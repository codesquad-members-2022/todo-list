import UIKit

class TodoTableViewCell: UITableViewCell {

    @IBOutlet private weak var titleLabel: UILabel!
    @IBOutlet private weak var contentLabel: UILabel!
    
    @IBOutlet weak var roundedView: UIView!
    
    static let cellName: String = "todoCell"
    static let cornerRadius: CGFloat = 10
    
    static var nib: UINib {
        return UINib(nibName: String(describing: self), bundle: nil)
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        roundedView.layer.cornerRadius = TodoTableViewCell.cornerRadius
        roundedView.layer.masksToBounds = true
    }
    
    func applyTextAllLabels(data card: CardData) {
        setTitleLabelAttribute(card.title)
        setContentLabelAttribute(card.contents)
    }

    private func setTitleLabelAttribute(_ text: String) {
        titleLabel.text = text
    }

    private func setContentLabelAttribute(_ text: String) {
        contentLabel.text = text
    }
}
