import UIKit

class TaskCardViewCell: UITableViewCell {
    static let nibName: String = "TaskCardViewCell"
    static let identifier: String = "cardCell"
    
    @IBOutlet weak var cardTitle: UILabel!
    @IBOutlet weak var cardContents: UILabel!
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    func setColor(to color: UIColor) {
        self.backgroundColor = color
    }
    
}
