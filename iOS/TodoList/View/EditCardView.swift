import UIKit

class EditCardView: UIView {
    
    @IBOutlet private weak var instruction: UILabel!
    @IBOutlet private weak var title: UITextField!
    @IBOutlet private weak var content: UITextField!
    @IBOutlet private weak var editButton: UIButton!
    @IBOutlet private weak var cancelButton: UIButton!
    
    @IBAction func editButtonTapped(_ sender: UIButton) {
    }
    @IBAction func cancelButtonTapped(_ sender: UIButton) {
    }
}
