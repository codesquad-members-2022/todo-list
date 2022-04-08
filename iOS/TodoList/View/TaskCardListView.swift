import UIKit

class TaskCardListView: UIView {
    @IBOutlet weak var table: UITableView!
    @IBOutlet weak var title: UILabel!
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

