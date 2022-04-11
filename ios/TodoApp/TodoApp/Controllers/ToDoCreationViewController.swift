import UIKit

class ToDoCreationViewController: UIViewController {
    
    @IBOutlet weak var editView: UIView!
    @IBOutlet weak var headField: UITextField!
    @IBOutlet weak var bodyField: UITextField!
    @IBOutlet weak var cancelButton: UIButton!
    @IBOutlet weak var registerButton: UIButton!
    
    private func configureUI() {
        editView.clipsToBounds = true
        editView.layer.cornerRadius = 15
        editView.layer.borderWidth = 0.3
        editView.layer.borderColor = UIColor.blue.cgColor
        
        headField.borderStyle = .none
        bodyField.borderStyle = .none
        headField.becomeFirstResponder()
        bodyField.addTarget(self, action: #selector(didBodyFieldChange(_:)), for: .editingChanged)
        headField.delegate = self
        bodyField.delegate = self
        registerButton.isHighlighted.toggle()
        cancelButton.addTarget(self, action: #selector(dismissView), for: .touchUpInside)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        hideKeyboardWhenTappedAround()
        configureUI()
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

