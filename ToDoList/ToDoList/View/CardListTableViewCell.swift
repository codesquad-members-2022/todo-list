import UIKit

class CardListTableViewCell: UITableViewCell {
    
    static let identifier = "CardListTableViewCell"
    
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var body: UILabel!
    @IBOutlet weak var caption: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        self.contentView.subviews.first?.layer.borderColor = selected ? UIColor(red: 0, green: 117/255, blue: 222/255, alpha: 1.0).cgColor : nil
        self.contentView.subviews.first?.layer.borderWidth = selected ? 1 : 0
    }
    
    func configure(title: String, body: String, caption: String = "iOS") {
        self.title.text = title
        self.body.text = body
        self.caption.text = "author by \(caption)"
    }
}
