import UIKit

class TaskListView: UIView {
    static let nibName: String = "TaskListView"
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var taskCountBadge: UILabel!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    @IBAction func addTaskButtonTouched(_ sender: UIButton) {
    }
}

