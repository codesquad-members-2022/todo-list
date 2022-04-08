import UIKit

class CardListViewController: UIViewController {
    
    var headerTitle = ""
    var data = ["1", "2", "3"]
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var countBadgeLabel: UILabel!
    @IBOutlet weak var addCardButton: UIButton!
    
    init(title: String) {
        super.init(nibName: "CardListView", bundle: nil)
        self.headerTitle = title
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.titleLabel.text = headerTitle
        self.countBadgeLabel.text = "0"

        self.tableView.dataSource = self
        self.tableView.register(UINib(nibName: CardListTableViewCell.identifier, bundle: nil), forCellReuseIdentifier: CardListTableViewCell.identifier)
    }
}

extension CardListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        self.countBadgeLabel.text = "\(self.data.count)"
        return self.data.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CardListTableViewCell.identifier, for: indexPath) as? CardListTableViewCell else {
            return UITableViewCell()
        }
        
        cell.setTitle(title: data[indexPath.item])
        cell.setBody(body: data[indexPath.item])
        cell.setCaption(caption: "iOS")
        
        return cell
    }
    
    
}
