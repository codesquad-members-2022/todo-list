import UIKit

class TodoTableViewCell: UITableViewCell {

    @IBOutlet private weak var titleLabel: UILabel!
    @IBOutlet private weak var contentLabel: UILabel!
    
    static let cellName: String = "todoCell"
    
    static var nib: UINib {
        return UINib(nibName: String(describing: self), bundle: nil)
    }

    func admitCardLabel(_ card: CardData) {
        setTitleLabelAttribute(card.title)
    }

    private func setTitleLabelAttribute(_ text: String) {
        titleLabel.text = text
    }

    private func setContentLabelAttribute(_ text: String) {
        contentLabel.text = text
    }
}
