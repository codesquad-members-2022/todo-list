import UIKit

class CardListViewController: UIViewController {
    
    var headerTitle = ""
    private let cardManager: CardManager
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var countBadgeLabel: UILabel!
    @IBOutlet weak var addCardButton: UIButton!
    
    init(title: String) {
        self.headerTitle = title
        self.cardManager = CardManager(listName: title)
        super.init(nibName: "CardListView", bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        let title = "unknown"
        self.headerTitle = title
        self.cardManager = CardManager(listName: title)
        super.init(coder: coder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.titleLabel.text = headerTitle
        self.countBadgeLabel.text = "0"

        self.tableView.dataSource = self
        self.tableView.register(UINib(nibName: CardListTableViewCell.identifier, bundle: nil), forCellReuseIdentifier: CardListTableViewCell.identifier)
        
        cardManager.add()
    }
    
    @IBAction func addCardButtonTouched(_ sender: UIButton) {
        cardManager.add()
        self.tableView.reloadData()
    }
}

extension CardListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        self.countBadgeLabel.text = "\(self.cardManager.count)"
        return self.cardManager.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CardListTableViewCell.identifier, for: indexPath) as? CardListTableViewCell else {
            return UITableViewCell()
        }
        
        let newCardModel = cardManager[indexPath.item]
        
        cell.setTitle(title: newCardModel.title)
        cell.setBody(body: newCardModel.body)
        cell.setCaption(caption: "iOS")
        
        return cell
    }
    
    
}
