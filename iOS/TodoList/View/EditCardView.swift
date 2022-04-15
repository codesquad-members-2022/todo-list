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
    
    func setPreviousCardData(with card: TaskCard?) {
        guard let card = card else {
            self.instruction.text = "새로운 카드 추가"
            self.editButton.titleLabel?.text = "등록"
            return
        }
        self.instruction.text = "카드 수정"
        self.editButton.titleLabel?.text = "수정"
        self.title.text = card.title
        self.content.text = card.content
    }
    
    @IBAction func editButtonTapped(_ sender: UIButton) {
        let inputData: (String) -> RequestCardData = { section in RequestCardData(section: section, title: self.title.text ?? "", content: self.content.text ?? "") }
        delegate?.didAddButtonTouched(completion: inputData)
    }
    @IBAction func cancelButtonTapped(_ sender: UIButton) {
        delegate?.didCancelButtonTouched()
    }
}

