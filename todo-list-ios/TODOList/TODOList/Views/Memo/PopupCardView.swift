import UIKit

class PopupCardView: UIView {
    
    @IBOutlet weak var alertLabel: UILabel!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var contentLabel: UILabel!
    
    @IBOutlet weak var cancelButton: UIButton!
    @IBOutlet weak var okButton: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        layer.cornerRadius = 10
        translatesAutoresizingMaskIntoConstraints = false
        backgroundColor = .white
        
        cancelButton.layer.cornerRadius = 6
        okButton.layer.cornerRadius = 6
    }
}
