import UIKit

class ProgressTodoViewController: UIViewController, TodoEndPointViewController {
    
    var middleWare: TodoCardViewMiddleWare?
    let boardType: TodoBoard = .progress
    
    @IBOutlet weak var tableView: TodoTableView!
    
    private var tableViewManagement: TodoTableViewManagement? {
        didSet {
            tableView.management = tableViewManagement
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableViewManagement = TodoTableViewManagement(tableView, in: boardType)
        setDataSource()
    }
    
    func setDataSource() {
        middleWare?.requestCards(in: boardType, { data in
            if let data = data {
                self.tableViewManagement?.setDataSource(data: data)
            }
        })
    }
}
