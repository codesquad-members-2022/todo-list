import UIKit

class TodoCreationViewController: UIViewController {
    
    @IBOutlet weak var editView: UIView!
    @IBOutlet weak var headField: UITextField!
    @IBOutlet weak var bodyField: UITextView!
    @IBOutlet weak var cancelButton: UIButton!
    @IBOutlet weak var registerButton: UIButton!
    
    private func configureUI() {
        editView.clipsToBounds = true
        editView.layer.cornerRadius = 15
        editView.layer.borderWidth = 0.3
        editView.layer.borderColor = UIColor.blue.cgColor
        bodyField.isScrollEnabled = false
        bodyField.autocorrectionType = .no
        headField.borderStyle = .none
        headField.becomeFirstResponder()
        registerButton.isHighlighted.toggle()
        cancelButton.addTarget(self, action: #selector(dismissView), for: .touchUpInside)
    }
    
    private func setDelegate() {
        headField.delegate = self
        bodyField.delegate = self
        hideKeyboardWhenTappedAround()
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillShow(_:)), name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillHide(_:)), name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
        setDelegate()
    }
}

extension TodoCreationViewController: UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        if textField === headField {
            headField.resignFirstResponder()
            bodyField.becomeFirstResponder()
        }
        return true
    }
    
    @objc func keyboardWillShow(_ sender: Notification) {
        self.view.frame.origin.y = -150
    }
    
    @objc func keyboardWillHide(_ sender: Notification) {
        self.view.frame.origin.y = 0
    }
}

// 배경 탭했을 때 키보드 해산 시키는 기능
extension TodoCreationViewController: UIGestureRecognizerDelegate {
    private func hideKeyboardWhenTappedAround() {
        let tap = UITapGestureRecognizer(target: self, action: #selector(dismissKeyboard))
        tap.cancelsTouchesInView = false
        self.view.addGestureRecognizer(tap)
    }
    
    @objc func dismissKeyboard() {
        self.view.endEditing(true)
    }
    
    @objc func dismissView() {
        self.dismiss(animated: true, completion: nil)
    }
}

extension TodoCreationViewController: UITextViewDelegate {
    // 글자수 제한
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        guard let string = textView.text else { return true }
        let maxCharactorLength: Int = 500
        let newLength = string.count + text.count - range.length
        return newLength <= maxCharactorLength
    }
    
    // 내용있어야 등록 버튼 활성화
    func textViewDidChange(_ textView: UITextView) {
        if textView.hasText {
            if registerButton.isHighlighted == true {
                registerButton.isHighlighted.toggle()
            }
        }
        else {
            registerButton.isHighlighted.toggle()
        }
    }
}


