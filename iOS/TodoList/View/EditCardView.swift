import UIKit

class EditCardView: UIView {
    
    @IBOutlet private weak var instruction: UILabel!
    @IBOutlet private weak var title: UITextField!
    @IBOutlet private weak var content: UITextField!
    @IBOutlet private weak var editButton: UIButton!
    @IBOutlet private weak var cancelButton: UIButton!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    @IBAction func editButtonTapped(_ sender: UIButton) {
        NotificationCenter.default.post(name: .editButtonTapped, object: nil)
    }
    @IBAction func cancelButtonTapped(_ sender: UIButton) {
        NotificationCenter.default.post(name: .cancelButtonTapped, object: nil)
    }
}
