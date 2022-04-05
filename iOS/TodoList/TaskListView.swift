import UIKit

class TaskListView: UIView {
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var taskCountBadge: UILabel!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        let className = String(describing: type(of: self))
        let nib = UINib(nibName: className, bundle: Bundle.main)
        
        guard let xibView = nib.instantiate(withOwner: self, options: nil).first as? UIView else { return }
        xibView.frame = self.bounds
        xibView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        self.addSubview(xibView)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    @IBAction func addTaskButtonTouched(_ sender: UIButton) {
    }
}
