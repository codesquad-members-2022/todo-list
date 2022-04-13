import UIKit
import OSLog

class CardListViewController: UIViewController {
    
    private var headerTitle = ""
    private let cardManager: CardManagable
    private var selectIndexPath: (IndexPath, Bool)?
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var countBadgeLabel: UILabel!
    @IBOutlet weak var addCardButton: UIButton!
    private weak var editCardViewModal: EditCardViewController? {
        didSet {
            addEditCardViewObserver()
        }
    }
    
    init(type: CardList, cardManager: CardManagable) {
        self.headerTitle = type.titleName
        self.cardManager = cardManager
        super.init(nibName: "CardListView", bundle: nil)
        
        addCardManagerObserver()
    }
    
    required init?(coder: NSCoder) {
        let title = "unknown"
        self.headerTitle = title
        self.cardManager = CardManager(cardListType: .todo, cardFactory: ModelFactory())
        super.init(coder: coder)
        
        addCardManagerObserver()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.titleLabel.text = headerTitle
        self.countBadgeLabel.text = "0"
        
        self.tableView.dataSource = self
        self.tableView.register(UINib(nibName: CardListTableViewCell.identifier, bundle: nil), forCellReuseIdentifier: CardListTableViewCell.identifier)
        
        self.tableView.delegate = self
        self.tableView.dragInteractionEnabled = true
        self.tableView.dropDelegate = self
        self.tableView.dragDelegate = self
    }
    
    @IBAction func addCardButtonTouched(_ sender: UIButton) {
        let editCardViewModal = EditCardViewController(nibName: "EditCardView", bundle: nil)
        self.editCardViewModal = editCardViewModal
        
        editCardViewModal.setSubjectLabel("새로운 카드 추가")
        
        editCardViewModal.modalPresentationStyle = .formSheet
        self.present(editCardViewModal, animated: true, completion: nil)
    }
    
    private func updateBadge() {
        self.countBadgeLabel.text = "\(self.cardManager.count)"
    }
}

extension CardListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.cardManager.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CardListTableViewCell.identifier, for: indexPath) as? CardListTableViewCell else {
            return UITableViewCell()
        }
        
        let newCardModel = cardManager[indexPath.item]
        
        cell.configure(title: newCardModel.title, body: newCardModel.body)
        
        return cell
    }
}

extension CardListViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            cardManager.remove(at: indexPath.item)
        }
    }
}

//MARK: - Handle EditCardView's notification
extension CardListViewController {
    
    private func addEditCardViewObserver() {
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(editCardViewModalDidAddNewData(_:)),
                                               name: EditCardViewController.Constants.NotificationNames.didAddNewData,
                                               object: self.editCardViewModal)
    }
    
    @objc private func editCardViewModalDidAddNewData(_ notification: Notification) {
        guard let newData = notification.userInfo?[EditCardViewController.Constants.userInfoKeys.addedData] as? [String] else {return}
        
        cardManager.add(title: newData[0], body: newData[1])
    }
}

//MARK: - Handle cardManager's notification
extension CardListViewController {
    private func addCardManagerObserver() {
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(cardManagerDidAddNewCard(_:)),
                                               name: CardManager.Constants.NotificationNames.didAddNewCard,
                                               object: self.cardManager)
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(cardManagerDidRemoveCard(_:)),
                                               name: CardManager.Constants.NotificationNames.didRemoveCard,
                                               object: self.cardManager)
    }
    
    @objc private func cardManagerDidAddNewCard(_ notification: Notification) {
        guard let addedCard = notification.userInfo?[CardManager.Constants.userInfoKeys.addedCard] as? Card else {
            return
        }
        
        NetworkManager.sendRequest(networkTarget: NetworkTarget.createCard(title: addedCard.title, body: addedCard.body, cardListId: addedCard.cardListID)) { result in
            switch result {
                case .success(let returnedCard):
                    self.cardManager.setNewCardsID(with: returnedCard.id ?? 0)
                case .failure(let error):
                    os_log(.error, "\(error.localizedDescription)")
            }
        }
        
        updateBadge()
        tableView.reloadData()
        
        NotificationCenter.default.removeObserver(self,
                                                  name: EditCardViewController.Constants.NotificationNames.didAddNewData,
                                                  object: self.editCardViewModal)
    }
    
    @objc private func cardManagerDidRemoveCard(_ notification: Notification) {
        guard let removedCard = notification.userInfo?[CardManager.Constants.userInfoKeys.removedCard] as? Card,
              let removedCardIndex = notification.userInfo?[CardManager.Constants.userInfoKeys.removedCardIndex] as? Int else {
                  return
              }
        
        NetworkManager.sendRequest(data: removedCard, httpMethod: .DELETE, targetColumnId: nil, targetCardId: nil) { result in
            switch result {
                case .success(_):
                    os_log(.default, "success remove")
                case .failure(let error):
                    os_log(.error, "\(error.localizedDescription)")
            }
        }
        
        tableView.deleteRows(at: [IndexPath(item: removedCardIndex, section: 0)], with: .automatic)
        updateBadge()
    }
//MARK: - Handle Drag and Drop
extension CardListViewController: UITableViewDragDelegate {
    func tableView(_ tableView: UITableView, itemsForBeginning session: UIDragSession, at indexPath: IndexPath) -> [UIDragItem] {
        
        guard let selectedCard = cardManager.selectCard(index: indexPath.item) as? Card else {
            return []
        }
        let itemProvider = NSItemProvider(object: selectedCard)
        
        selectIndexPath = (indexPath, false)
        
        return [UIDragItem(itemProvider: itemProvider)]
    }
    
    func tableView(_ tableView: UITableView, dragSessionDidEnd session: UIDragSession) {
        guard let selectIndexPath = selectIndexPath else {return}
        if !selectIndexPath.1 {
            if tableView == self.tableView {
                cardManager.remove(at: selectIndexPath.0.row, isMovingState: true)
            }
            
            tableView.reloadData()
            updateBadge()
        }
    }
    
}

extension CardListViewController: UITableViewDropDelegate {
    func tableView(_ tableView: UITableView, performDropWith coordinator: UITableViewDropCoordinator) {
        var destinationIndexPath = IndexPath(row: tableView.numberOfRows(inSection: 0), section: 0)
        
        if let indexPath = coordinator.destinationIndexPath {
            destinationIndexPath = indexPath
        }
        
        coordinator.session.loadObjects(ofClass: Card.self) { [self] items in
            guard let cards = items as? [Card] else {return}
            var indexPaths = [IndexPath]()
            
            for (index, value) in cards.enumerated() {
                let indexPath = IndexPath(row: destinationIndexPath.row + index, section: destinationIndexPath.section)
                
                if tableView == self.tableView {
                    cardManager.add(newCard: value, at: indexPath.row)
                }
                indexPaths.append(indexPath)
            }
            tableView.reloadData()
            updateBadge()
        }
    }
    
    func tableView(_ tableView: UITableView, canHandle session: UIDropSession) -> Bool {
        return session.canLoadObjects(ofClass: Card.self)
    }
    
    func tableView(_ tableView: UITableView, dropSessionDidUpdate session: UIDropSession, withDestinationIndexPath destinationIndexPath: IndexPath?) -> UITableViewDropProposal {
        var dropProposal = UITableViewDropProposal(operation: .cancel)
        guard session.items.count == 1 else {return dropProposal}
        
        if tableView.hasActiveDrag {
            if tableView.isEditing {
                dropProposal = UITableViewDropProposal(operation: .move, intent: .insertAtDestinationIndexPath)
            }
        } else {
            if let indexPath = selectIndexPath {
                selectIndexPath = (indexPath.0, true)
            }
            
            return UITableViewDropProposal(operation: .copy, intent: .insertAtDestinationIndexPath)
        }
        
        return dropProposal
    }
}
