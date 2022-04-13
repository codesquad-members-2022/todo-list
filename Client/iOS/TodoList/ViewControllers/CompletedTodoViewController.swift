import UIKit

class CompletedTodoViewController: UIViewController, TodoEndPointViewController {
    let middleWare = AppDelegate.middleWare
    let boardType: TodoBoard = .completed
    
    @IBOutlet weak var tableView: TodoTableView!
    
    private var tableViewManagement: TodoTableViewManagement?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableViewManagement = TodoTableViewManagement(tableView, in: boardType)
    }
}
