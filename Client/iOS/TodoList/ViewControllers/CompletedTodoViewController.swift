import UIKit

class CompletedTodoViewController: UIViewController, TodoEndPointViewController, TodoBadgeDelegate {
    let middleWare = AppDelegate.middleWare
    let boardType: TodoBoard = .completed
    
    @IBOutlet weak var tableView: TodoTableView!
    @IBOutlet weak var dataSourceBadge: UILabel!
    
    private var tableViewManagement: TodoTableViewManagement?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableViewManagement = TodoTableViewManagement(tableView, in: boardType)
        tableViewManagement?.badgeDelegate = self
    }
    
    func setBadgeCount(_ count: Int) {
        dataSourceBadge.text = "\(count)"
        dataSourceBadge.layer.cornerRadius = dataSourceBadge.frame.width / 2
    }
}
