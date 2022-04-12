//
//  EditCardView.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/11.
//

import UIKit

final class EditCardView:UIView {
    
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
        button.backgroundColor = UIColor(red: 224 / 255, green: 224 / 255, blue: 224 / 255, alpha: 1)
        button.layer.cornerRadius = 6
        return button
    }()
    
    private lazy var confirmButton:UIButton = {
        let button = UIButton()
        button.backgroundColor = UIColor(red: 134 / 255, green: 198 / 255, blue: 255 / 255, alpha: 1)
        button.layer.cornerRadius = 6
        button.isEnabled = false
        return button
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addViews()
        setup()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        addViews()
        setup()
    }
    
    private func addViews() {
        [titleLabel,headLineInputTextField,contentInputTextField,cancelButton,confirmButton].forEach {
            $0.translatesAutoresizingMaskIntoConstraints = false
            self.addSubview($0)
        }
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

//MARK: -- Set CardView Detail
extension EditCardView {
    func setEditCardView(editStyle:EditStyle) {
        self.titleLabel.text = editStyle.title
        self.headLineInputTextField.placeholder = editStyle.headLineTextFieldPlaceholder
        self.contentInputTextField.placeholder = editStyle.contentTextFieldPlaceholder
        self.confirmButton.setTitle(editStyle.buttonText, for: .normal)
    }
}
