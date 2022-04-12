import UIKit
import OSLog

class CardListViewController: UIViewController {
    
    private var headerTitle = ""
    private let cardManager: CardManagable
    
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
    }
    
    @objc private func cardManagerDidAddNewCard(_ notification: Notification) {
        guard let addedCard = notification.userInfo?[CardManager.Constants.userInfoKeys.addedCard] as? Card else {
            return
        }
        
        NetworkManager.sendRequest(data: addedCard, httpMethod: .POST, targetColumnId: nil, targetCardId: nil) { result in
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
}
