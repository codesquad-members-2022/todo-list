//
//  TodoViewController.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import Foundation
import UIKit
import Combine

protocol CardsColumnView {
    var controller: UIViewController { get }
}

class ColumnViewController: UIViewController, CardsColumnView {
    
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "해야할 일"
        label.font = .systemFont(ofSize: 18, weight: .bold)
        return label
    }()
    
    private let count: UILabel = {
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
    
    private let cardTable: UITableView = {
        let tableView = UITableView()
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.register(ColumnViewCell.self, forCellReuseIdentifier: "ColumnViewCell")
        tableView.backgroundColor = .clear
        tableView.separatorStyle = .none
        return tableView
    }()
    
    private let add: UIButton = {
        var configuration = UIButton.Configuration.plain()
        configuration.contentInsets = .init(top: 16, leading: 16, bottom: 16, trailing: 16)

        let button = UIButton(configuration: configuration, primaryAction: nil)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setImage(UIImage(named: "ic_add"), for: .normal)
        return button
    }()
    
    var controller: UIViewController {
        self
    }
    
    private var cancellables = Set<AnyCancellable>()
    private let model: ColumnViewModelBinding & ColumnViewModelProperty = ColumnViewModel()
    private let status: Card.Status
    
    init(status: Card.Status) {
        self.status = status
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        self.status = .todo
        super.init(coder: coder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bind()
        attribute()
        layout()
        gesture()
        
        model.action.loadColumn.send(self.status)
    }
    
    private func bind() {
        cardTable.delegate = self
        cardTable.dataSource = self
        
        add.publisher(for: .touchUpInside)
            .sink{
                self.showCardPopup(card: nil)
            }.store(in: &cancellables)
        
        self.model.state.loadedColumn
            .sink { cards in
                self.cardTable.reloadData()
                self.count.text = String(cards?.count ?? 0 )
            }.store(in: &cancellables)
    }
    
    private func attribute() {
        self.titleLabel.text = status.titleName
    }
    
    private func layout() {
        self.view.addSubview(titleLabel)
        titleLabel.topAnchor.constraint(equalTo: self.view.topAnchor).isActive = true
        titleLabel.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 8).isActive = true
        
        self.view.addSubview(count)
        count.leftAnchor.constraint(equalTo: titleLabel.rightAnchor, constant: 8).isActive = true
        count.centerYAnchor.constraint(equalTo: titleLabel.centerYAnchor).isActive = true
        count.heightAnchor.constraint(equalToConstant: 26).isActive = true
        
        self.view.addSubview(add)
        add.centerYAnchor.constraint(equalTo: titleLabel.centerYAnchor).isActive = true
        add.rightAnchor.constraint(equalTo: self.view.rightAnchor, constant: -8).isActive = true
        add.widthAnchor.constraint(equalToConstant: 24).isActive = true
        add.heightAnchor.constraint(equalToConstant: 24).isActive = true
        
        self.view.addSubview(cardTable)
        cardTable.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 16).isActive = true
        cardTable.leftAnchor.constraint(equalTo: self.view.leftAnchor).isActive = true
        cardTable.rightAnchor.constraint(equalTo: self.view.rightAnchor).isActive = true
        cardTable.bottomAnchor.constraint(equalTo: self.view.bottomAnchor, constant:  -10).isActive = true
    }
    
    private func gesture() {
    }
    
    private func showCardPopup(card: Card? = nil) {
        let popup = CardPopupController(card: card)
        popup.modalPresentationStyle = .overCurrentContext
        self.present(popup, animated: false)
        
        popup.confimPublisher
            .sink(receiveValue: self.model.action.newCard.send(_:))
            .store(in: &self.cancellables)
    }
}

extension ColumnViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        self.model.cardCount
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "ColumnViewCell") as? ColumnViewCell,
              let card = self.model[indexPath.item] else {
            return UITableViewCell()
        }
        cell.setCard(card)
        return cell
    }
    
    func tableView(_ tableView: UITableView, contextMenuConfigurationForRowAt indexPath: IndexPath, point: CGPoint) -> UIContextMenuConfiguration? {
        
        return UIContextMenuConfiguration(identifier: nil, previewProvider: nil) { _ in
            let moveDone = UIAction(title: "완료한 일로 이동") { _ in
                self.model.action.moveCard.send(indexPath.item)
            }
            
            let edit = UIAction(title: "수정하기") { _ in
                self.showCardPopup(card: self.model[indexPath.item])
            }
            
            let delete = UIAction(title: "삭제하기", attributes: .destructive) { _ in
                self.model.action.deleteCard.send(indexPath.item)
            }
            
            return UIMenu(title: "", children: [moveDone, edit, delete])
        }
    }
}

