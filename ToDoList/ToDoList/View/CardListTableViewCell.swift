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
    }
    
    func configure(title: String, body: String, caption: String = "iOS") {
        self.title.text = title
        self.body.text = body
        self.caption.text = "author by \(caption)"
    }    
}
