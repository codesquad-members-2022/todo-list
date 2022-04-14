import UIKit

class LogListViewCell: UITableViewCell {
    
    static let identifier = "LogListViewCell"
    
    @IBOutlet weak var userName: UILabel!
    @IBOutlet weak var body: UILabel!
    @IBOutlet weak var timeStamp: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
    }

    func setUserName(name: String) {
        self.userName.text = name
    }
    
    func setBody(body: String) {
        self.body.text = body
    }
    
    func setTimeStamp(time: String) {
        // updateTimeStamp()
        self.timeStamp.text = time
    }
    
//    func updateTimeStamp() {
//
//    }
    
}
