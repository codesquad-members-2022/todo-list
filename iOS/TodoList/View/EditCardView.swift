import UIKit

protocol EditCardViewDelegate {
    func didCancelButtonTouched()
    
    func didAddButtonTouched(completion: (String) -> RequestCardData)
}

class EditCardView: UIView {
    
    @IBOutlet private weak var instruction: UILabel!
    @IBOutlet private weak var title: UITextField!
    @IBOutlet private weak var content: UITextField!
    @IBOutlet private weak var editButton: UIButton!
    @IBOutlet private weak var cancelButton: UIButton!
    
    var delegate: EditCardViewDelegate?
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    @IBAction func editButtonTapped(_ sender: UIButton) {
        let inputData: (String) -> RequestCardData = { section in RequestCardData(section: section, title: self.title.text ?? "", content: self.content.text ?? "") }
        delegate?.didAddButtonTouched(completion: inputData)
    }
    @IBAction func cancelButtonTapped(_ sender: UIButton) {
        delegate?.didCancelButtonTouched()
    }
}

