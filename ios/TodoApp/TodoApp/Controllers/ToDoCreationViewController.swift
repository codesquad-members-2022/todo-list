import UIKit

class ToDoCreationViewController: UIViewController {
    
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
        headField.borderStyle = .none
        headField.becomeFirstResponder()
        registerButton.isHighlighted.toggle()
        cancelButton.addTarget(self, action: #selector(dismissView), for: .touchUpInside)
    }
    
    private func setDelegate() {
        headField.delegate = self
//        bodyField.delegate = self
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

extension ToDoCreationViewController: UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        if textField === headField {
            headField.resignFirstResponder()
            bodyField.becomeFirstResponder()
        }
        if textField === bodyField {
            // 등록버튼을 누른 것과 같이 활동
            bodyField.resignFirstResponder()
        }
        return true
    }
    
    @objc func didBodyFieldChange(_ textField: UITextField) {
        if textField.hasText {
            if registerButton.isHighlighted == true {
                registerButton.isHighlighted.toggle()
            }
        } else {
            registerButton.isHighlighted.toggle()
        }
    }
    
    @objc func keyboardWillShow(_ sender: Notification) {
        self.view.frame.origin.y = -150
    }
    
    @objc func keyboardWillHide(_ sender: Notification) {
        self.view.frame.origin.y = 0
    }
}

extension ToDoCreationViewController: UIGestureRecognizerDelegate {
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

extension ToDoCreationViewController: UITextViewDelegate {
    func textViewDidChange(_ textView: UITextView) {
        let size: CGSize = CGSize(width: view.frame.width, height: .infinity)
        let estimatedSize = textView.sizeThatFits(size)
        
        textView.constraints.forEach { (constraint) in
            if estimatedSize.height <= 30 {
                
            }
            else {
                if constraint.firstAttribute == .height {
                    constraint.constant = estimatedSize.height
                }
            }
        }
    }
}
