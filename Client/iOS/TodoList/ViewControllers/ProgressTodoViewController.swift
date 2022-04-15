import UIKit

class ProgressTodoViewController: UIViewController, TodoEndPointViewController, TodoBadgeDelegate {
    let middleWare = AppDelegate.middleWare
    let boardType: TodoBoard = .progress
    
    @IBOutlet weak var tableView: TodoTableView!
    @IBOutlet weak var dataSourceBadge: UILabel!
    
    private var repositoryResponder: TodoRepositoryRespondWrapper?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        repositoryResponder = TodoRepositoryRespondWrapper(tableView, in: boardType)
        repositoryResponder?.badgeDelegate = self
    }
    
    func setBadgeCount(_ count: Int) {
        dataSourceBadge.text = "\(count)"
        dataSourceBadge.layer.cornerRadius = dataSourceBadge.frame.width / 2
    }
}
