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
    
    func setTitle(title: String) {
        self.title.text = title
    }
    
    func setBody(body: String) {
        self.body.text = body
    }
    
    func setCaption(caption: String) {
        self.caption.text = "author by \(caption)"
    }
    
}
