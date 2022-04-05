import UIKit

class TaskCardViewCell: UITableViewCell {
    
    @IBOutlet weak var cardTitle: UILabel!
    @IBOutlet weak var cardContents: UILabel!
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        layoutSubviews()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        layoutSubviews()
    }
    
    func setColor(to color: UIColor) {
        self.backgroundColor = color
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()

        contentView.frame = contentView.frame.inset(by: UIEdgeInsets(top: 10, left: 10, bottom: 0, right: 10))
    }
}
