import UIKit

class ActionCardViewCell: UITableViewCell {
    
    @IBOutlet private weak var eventImage: UILabel!
    @IBOutlet private weak var userName: UILabel!
    @IBOutlet private weak var content: UILabel!
    @IBOutlet private weak var timeStamp: UILabel!
    
    func setData(image: String, content: String, timeStamp: String) {
        self.eventImage.text = image
        self.content.text = content
        self.timeStamp.text = timeStamp
    }

}
