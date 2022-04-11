//
//  TodoViewController.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import Combine
import UIKit

protocol ColumnViewDelegate {
    func columnView(_ columnView: ColumnViewController, fromCard: Card, toColumn: Column.ColumnType)
}

protocol ColumnViewInput {
    func addCard(_ card: Card, at toIndex: Int)
}

typealias ColumnViewControllerProtocol = UIViewController & ColumnViewInput

class ColumnViewController: UIViewController {
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "해야할 일"
        label.font = .systemFont(ofSize: 18, weight: .bold)
        return label
    }()
    
    private let countLabel: UILabel = {
        let label = PaddingLabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "100"
        label.font = .systemFont(ofSize: 14, weight: .bold)
        label.textColor = .black
        label.backgroundColor = .gray4
        label.padding = .init(top: 8, left: 9, bottom: 8, right: 9)
        label.layer.masksToBounds = true
        label.layer.cornerRadius = 13
        return label
    }()
    
    private let cardTableView: UITableView = {
        let tableView = UITableView()
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.register(ColumnViewCell.self, forCellReuseIdentifier: "ColumnViewCell")
        tableView.backgroundColor = .clear
        tableView.separatorStyle = .none
        return tableView
    }()
    
    private let addButton: UIButton = {
        var configuration = UIButton.Configuration.plain()
        configuration.contentInsets = .init(top: 16, leading: 16, bottom: 16, trailing: 16)

        let button = UIButton(configuration: configuration, primaryAction: nil)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setImage(UIImage(named: "ic_add"), for: .normal)
        return button
    }()
    
    private var cancellables = Set<AnyCancellable>()
    private let model: ColumnViewModelProtocol
    
    var delegate: ColumnViewDelegate?
    
    init(model: ColumnViewModelProtocol) {
        self.model = model
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        self.model = ColumnViewModel(column: Column(type: .todo, cards: []))
        super.init(coder: coder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bind()
        layout()
        
        model.action.viewDidLoad.send()
    }
    
    private func bind() {
        cardTableView.delegate = self
        cardTableView.dataSource = self
        
        cardTableView.dragInteractionEnabled = true
        cardTableView.dragDelegate = self
        cardTableView.dropDelegate = self
        
        addButton.publisher(for: .touchUpInside)
            .sink(receiveValue: model.action.tappedAddButton.send(_:))
            .store(in: &cancellables)
        
        model.state.showCardPopup
            .sink(receiveValue: self.showCardPopup(_:))
            .store(in: &cancellables)
        
        model.state.loadedColumn
            .sink { colunm in
                self.titleLabel.text = colunm.titleName
                self.countLabel.text = String(self.model.cardCount)
                self.cardTableView.reloadData()
            }.store(in: &cancellables)
        
        model.state.insertedCard
            .sink {
                self.cardTableView.insertSections(IndexSet(integer: $0), with: .none)
                self.countLabel.text = String(self.model.cardCount)
            }.store(in: &cancellables)
        
        model.state.deletedCard
            .sink {
                self.cardTableView.deleteSections(IndexSet(integer: $0), with: .none)
                self.countLabel.text = String(self.model.cardCount)
            }.store(in: &cancellables)
        
        model.state.movedCard
            .sink { card, toColumn in
                self.delegate?.columnView(self, fromCard: card, toColumn: toColumn)
                self.countLabel.text = String(self.model.cardCount)
            }.store(in: &cancellables)
        
        model.state.reloadCard
            .sink {
                self.cardTableView.reloadRows(at: [IndexPath(item: 0, section: $0)], with: .none)
            }.store(in: &cancellables)
    }
    
    private func layout() {
        view.addSubview(titleLabel)
        view.addSubview(countLabel)
        view.addSubview(addButton)
        view.addSubview(cardTableView)
        
        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: view.topAnchor),
            titleLabel.leftAnchor.constraint(equalTo: view.leftAnchor, constant: 8),
            
            countLabel.leftAnchor.constraint(equalTo: titleLabel.rightAnchor, constant: 8),
            countLabel.centerYAnchor.constraint(equalTo: titleLabel.centerYAnchor),
            countLabel.heightAnchor.constraint(equalToConstant: 26),
            
            addButton.centerYAnchor.constraint(equalTo: titleLabel.centerYAnchor),
            addButton.rightAnchor.constraint(equalTo: view.rightAnchor, constant: -8),
            addButton.widthAnchor.constraint(equalToConstant: 24),
            addButton.heightAnchor.constraint(equalToConstant: 24),
            
            cardTableView.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 16),
            cardTableView.leftAnchor.constraint(equalTo: view.leftAnchor),
            cardTableView.rightAnchor.constraint(equalTo: view.rightAnchor),
            cardTableView.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant:  -10)
        ])
    }
    
    private func showCardPopup(_ popupData: CardPopupData) {
        let popup = CardPopupViewController(model: CardPopupViewModel(popupData: popupData) )
        popup.modalPresentationStyle = .overCurrentContext
        present(popup, animated: false)
        popup.delegate = self
    }
}

extension ColumnViewController: UITableViewDragDelegate {
    func tableView(_ tableView: UITableView, itemsForBeginning session: UIDragSession, at indexPath: IndexPath) -> [UIDragItem] {
        guard let card = model[indexPath.section] else {
            return []
        }
        model.action.beginDrag.send(card)
        return [UIDragItem(itemProvider: NSItemProvider(object: DragCard(card: card)))]
    }
    
    func tableView(_ tableView: UITableView, dragSessionDidEnd session: UIDragSession) {
        model.action.endDrag.send()
    }
}

extension ColumnViewController: UITableViewDropDelegate {
    func tableView(_ tableView: UITableView, canHandle session: UIDropSession) -> Bool {
        session.canLoadObjects(ofClass: DragCard.self)
    }
    
    func tableView(_ tableView: UITableView, dropSessionDidUpdate session: UIDropSession, withDestinationIndexPath destinationIndexPath: IndexPath?) -> UITableViewDropProposal {
        guard session.items.count == 1 else { return UITableViewDropProposal(operation: .cancel) }
        return UITableViewDropProposal(operation: .move, intent: .insertAtDestinationIndexPath)
    }
    
    func tableView(_ tableView: UITableView, performDropWith coordinator: UITableViewDropCoordinator) {
        var destinationIndexPath = IndexPath(row: tableView.numberOfRows(inSection: 0), section: 0)
        if let indexpath = coordinator.destinationIndexPath {
            destinationIndexPath = indexpath
        }
        
        coordinator.session.loadObjects(ofClass: DragCard.self) { items in
            let dragCards = items.compactMap{ $0 as? DragCard }
            
            dragCards.forEach { dragCard in
                guard let addCard = dragCard.card else {
                    return
                }
                print(destinationIndexPath.section)
                self.model.action.addCard.send((addCard, destinationIndexPath.section + destinationIndexPath.item))
            }
        }
    }
}


extension ColumnViewController: UITableViewDelegate, UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        model.cardCount
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        1
    }
    
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        16
    }
    
    func tableView(_ tableView: UITableView, titleForFooterInSection section: Int) -> String? {
        " "
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "ColumnViewCell") as? ColumnViewCell,
              let card = model[indexPath.section] else {
            return UITableViewCell()
        }
        cell.setCard(card)
        return cell
    }
    
    func tableView(_ tableView: UITableView, trailingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        let action = UIContextualAction(style: .destructive, title: "삭제") { [weak self] action, view, completionHandler in
            self?.model.action.tappedDeleteButton.send(indexPath.section)
            completionHandler(true)
        }
        let config = UISwipeActionsConfiguration(actions: [action])
        config.performsFirstActionWithFullSwipe = false

        return config
    }
    
    func tableView(_ tableView: UITableView, contextMenuConfigurationForRowAt indexPath: IndexPath, point: CGPoint) -> UIContextMenuConfiguration? {
        
        return UIContextMenuConfiguration(identifier: nil, previewProvider: nil) { [weak self] _ in
            let moveDone = UIAction(title: "완료한 일로 이동") { _ in
                self?.model.action.tappedMoveCardButton.send(indexPath.section)
            }
            
            let edit = UIAction(title: "수정하기") { _ in
                self?.model.action.tappedEditButton.send(indexPath.section)
            }
            
            let delete = UIAction(title: "삭제하기", attributes: .destructive) { _ in
                self?.model.action.tappedDeleteButton.send(indexPath.section)
            }
            return UIMenu(title: "", children: [moveDone, edit, delete])
        }
    }
}

extension ColumnViewController: ColumnViewInput {
    func addCard(_ card: Card, at toIndex: Int) {
        model.action.addCard.send((card, toIndex))
    }
}

extension ColumnViewController: CardPopupViewDeletegate {
    func cardPopupView(_ cardPopupView: CardPopupViewController, addedCard: Card, toIndex: Int) {
        model.action.addCard.send((addedCard, toIndex))
    }
    
    func cardPopupView(_ cardPopupView: CardPopupViewController, editedCard: Card) {
        model.action.editCard.send(editedCard)
    }
}

extension Column.ColumnType {
    var titleName: String {
        switch self {
        case .todo:
            return "해야할 일"
        case .progress:
            return "하고 있는 일"
        case .done:
            return "완료한 일"
        }
    }
}
