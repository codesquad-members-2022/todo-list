//
//  EditCardView.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/11.
//

typealias EditViewInputInfo = (button:String, title:String, content:String)

import UIKit

final class EditCardView:UIView {
    
    //Setting Value
    private let defaultButtonColor = UIColor(red: 224 / 255, green: 224 / 255, blue: 224 / 255, alpha: 1)
    private let activeButtonColor = UIColor(red: 0 , green: 117 / 255, blue: 222 / 255, alpha: 1)
    private let buttonCornerRadius:CGFloat = 6.0
        
    var delegate:EditCardViewDelegate?

    private lazy var titleLabel:UILabel = {
        let label = UILabel()
        label.font = .systemFont(ofSize: 16.0, weight: .bold)
        return label
    }()
    
    private lazy var headLineInputTextField:UITextField = {
        let textField = UITextField()
        return textField
    }()
    
    private lazy var contentInputTextField:UITextField = {
        let textField = UITextField()
        return textField
    }()
    
    private lazy var cancelButton:UIButton = {
        let button = UIButton()
        button.setTitle("취소", for: .normal)
        button.backgroundColor = defaultButtonColor
        button.layer.cornerRadius = buttonCornerRadius
        return button
    }()
    
    private lazy var confirmButton:UIButton = {
        let button = UIButton()
        button.backgroundColor = defaultButtonColor
        button.layer.cornerRadius = buttonCornerRadius
        button.isEnabled = false
        return button
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addViews()
        addAction()
        setTextFieldDelegate()
        setup()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        addViews()
        addAction()
        setTextFieldDelegate()
        setup()
    }
    
    
    func changeConfirmButtonState() {
        if isConfirmButtonEnable() {
            self.confirmButton.isEnabled = true
            self.confirmButton.backgroundColor = activeButtonColor
        } else {
            self.confirmButton.isEnabled = false
            self.confirmButton.backgroundColor = defaultButtonColor
        }
    }
    
    private func isConfirmButtonEnable() -> Bool {
        if headLineInputTextField.text?.count ?? 0 > 0,
           contentInputTextField.text?.count ?? 0 > 0 {
            return true
        } else {
            return false
        }
    }
    
    private func setTextFieldDelegate() {
        headLineInputTextField.delegate = self
        contentInputTextField.delegate = self
    }
    
    private func addViews() {
        [titleLabel,headLineInputTextField,contentInputTextField,cancelButton,confirmButton].forEach {
            $0.translatesAutoresizingMaskIntoConstraints = false
            self.addSubview($0)
        }
    }
    
    private func addAction() {
        self.cancelButton.addTarget(self, action: #selector(didTapCancelButton), for: .touchUpInside)
        self.confirmButton.addTarget(self, action: #selector(didTapConfirmButton), for: .touchUpInside)
    }
    
    private func setup() {
        let inset:CGFloat = 8.0
        let buttonSize:CGSize = CGSize(width: 108, height: 40)
        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: self.topAnchor, constant: inset * 2),
            titleLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: inset),
            
            headLineInputTextField.topAnchor.constraint(equalTo: self.titleLabel.bottomAnchor, constant: inset * 2),
            headLineInputTextField.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: inset),
            headLineInputTextField.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            
            contentInputTextField.topAnchor.constraint(equalTo: self.headLineInputTextField.bottomAnchor, constant: inset),
            contentInputTextField.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: inset),
            contentInputTextField.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            
            confirmButton.widthAnchor.constraint(equalToConstant: buttonSize.width),
            confirmButton.heightAnchor.constraint(equalToConstant: buttonSize.height),
            confirmButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -inset),
            confirmButton.topAnchor.constraint(equalTo: self.contentInputTextField.bottomAnchor, constant: inset),

            
            cancelButton.widthAnchor.constraint(equalToConstant: buttonSize.width),
            cancelButton.heightAnchor.constraint(equalToConstant: buttonSize.height),
            cancelButton.trailingAnchor.constraint(equalTo: self.confirmButton.leadingAnchor, constant: -inset),
            cancelButton.topAnchor.constraint(equalTo: self.contentInputTextField.bottomAnchor, constant: inset),
            
        ])
    }
}

//MARK: -- Set CardView Detail -> ChildView가 생성하기전 호출
extension EditCardView {
    func setEditCardView(editStyle:EditStyle) {
        self.titleLabel.text = editStyle.title
        self.headLineInputTextField.placeholder = editStyle.headLineTextFieldPlaceholder
        self.contentInputTextField.placeholder = editStyle.contentTextFieldPlaceholder
        self.confirmButton.setTitle(editStyle.buttonText, for: .normal)
    }
}

//MARK: -- TexFieldDelegate
extension EditCardView:UITextFieldDelegate {
    func textFieldDidEndEditing(_ textField: UITextField) {
        delegate?.textFieldDidEndEditing()
    }
}

//MARK: -- ButtonAction
extension EditCardView {
    @objc func didTapCancelButton() {
        delegate?.didTapCancelButton()
    }
    
    @objc func didTapConfirmButton() {
        guard let buttonTitle = confirmButton.titleLabel?.text else { return }
        let titleText = headLineInputTextField.text ?? ""
        let contentText = contentInputTextField.text ?? ""
        delegate?.didTapConfirmButton(editViewInfo: EditViewInputInfo(button:buttonTitle, title:titleText, content:contentText))
    }
}


