import UIKit

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
    
    init(title: String, cardManager: CardManagable) {
        self.headerTitle = title
        self.cardManager = cardManager
        super.init(nibName: "CardListView", bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        let title = "unknown"
        self.headerTitle = title
        self.cardManager = CardManager(listName: title, cardFactory: ModelFactory())
        super.init(coder: coder)
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
        
        updateBadge()
        tableView.reloadData()
    }
}
