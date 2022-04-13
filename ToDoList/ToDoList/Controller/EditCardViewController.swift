import UIKit

class EditCardViewController: UIViewController {
    
    private var subject = ""
    
    @IBOutlet weak var subjectLabel: UILabel!
    @IBOutlet weak var titleTextField: UITextField!
    @IBOutlet weak var bodyTextView: UITextView!
    @IBOutlet weak var addButton: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.subjectLabel.text = subject
        
        self.preferredContentSize = CGSize(width: 400, height: 175)
        
        setBodyTextViewInitialState()
        setButtonInitialState()
    }
    
    func setSubjectLabel(_ text: String) {
        self.subject = text
    }
    
    private func setBodyTextViewInitialState() {
        bodyTextView.delegate = self
        
        bodyTextView.text = EditCardViewController.bodyTextViewDefaultText
        bodyTextView.textColor = EditCardViewController.bodyTextViewDefaultPlaceholderColor
        bodyTextView.textContainer.lineFragmentPadding = 0
        bodyTextView.textContainerInset = UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)
    }
    
    private func setButtonInitialState() {
        addButton.isEnabled = false
        addButton.backgroundColor = EditCardViewController.addButtonDefaultColor
        addButton.setTitleColor(EditCardViewController.addButtonTitleDefaultColor, for: .disabled)
    }
    
    @IBAction func cancelButtonTouched(_ sender: UIButton) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func addButtonTouched(_ sender: UIButton) {
        let newData = [titleTextField.text, bodyTextView.text]
        let userInfo = [userInfoKeys.addedData: newData]
        
        NotificationCenter.default.post(name: NotificationNames.didAddNewData, object: self, userInfo: userInfo)
        
        self.dismiss(animated: true, completion: nil)
    }
}

extension EditCardViewController: UITextViewDelegate {
    func textViewDidBeginEditing(_ textView: UITextView) {
        if textView.textColor == EditCardViewController.bodyTextViewDefaultPlaceholderColor {
            textView.text = nil
            textView.textColor = EditCardViewController.bodyTextViewDefaultTextColor
        }
    }
    
    func textViewDidChange(_ textView: UITextView) {
        if textView.text == "" || textView.text == nil {
            bodyTextView.text = EditCardViewController.bodyTextViewDefaultText
            bodyTextView.textColor = EditCardViewController.bodyTextViewDefaultPlaceholderColor
            addButton.isEnabled = false
        } else if titleTextField.text != nil && titleTextField.text != "" {
            addButton.isEnabled = true
        }
    }
    
    func textViewDidEndEditing(_ textView: UITextView) {
        if textView.text == "" || textView.text == nil {
            bodyTextView.text = EditCardViewController.bodyTextViewDefaultText
            bodyTextView.textColor = EditCardViewController.bodyTextViewDefaultPlaceholderColor
            addButton.isEnabled = false
        } else if titleTextField.text != nil && titleTextField.text != "" {
            addButton.isEnabled = true
        }
    }
}

extension EditCardViewController {
    static let addButtonDefaultColor = UIColor(red: 148/255, green: 196/255, blue: 250/255, alpha: 1.0)
    static let addButtonTitleDefaultColor = UIColor(red: 1, green: 1, blue: 1, alpha: 0.4)
    static let bodyTextViewDefaultPlaceholderColor = UIColor.systemGray3
    static let bodyTextViewDefaultTextColor = UIColor.black
    static let bodyTextViewDefaultText = "내용을 입력하세요"
    
    enum NotificationNames {
        static let didAddNewData = Notification.Name("EditCardViewDidAddNewData")
    }
    
    enum userInfoKeys {
        static let addedData = "addedData"
    }
}
