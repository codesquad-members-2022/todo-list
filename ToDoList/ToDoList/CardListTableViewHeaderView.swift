import UIKit

class CardListTableViewHeaderView: UITableViewHeaderFooterView {
    
    static let identifier = "CardListTableViewHeaderView"

    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var badgeStackView: UIStackView!
    @IBOutlet weak var badgeLabel: UILabel!
    @IBOutlet weak var addCardButton: UIButton!

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commonInit()
    }
    
    override init(reuseIdentifier: String?) {
        super.init(reuseIdentifier: reuseIdentifier)
        
        commonInit()
    }
    
    func configure(title: String) {
        self.titleLabel.text = title
        self.badgeLabel.text = "0"
    }
    
    private func commonInit() {
        if let view = Bundle.main.loadNibNamed(CardListTableViewHeaderView.identifier, owner: self, options: nil)?.first as? UIView {
            view.frame = self.bounds
            addSubview(view)
        }
        self.badgeStackView.clipsToBounds = true
        self.badgeStackView.layer.cornerRadius = self.badgeStackView.frame.height/2
    }
    
    func changeCardCount(to number: Int) {
        self.badgeLabel.text = "\(number)"
    }
}
