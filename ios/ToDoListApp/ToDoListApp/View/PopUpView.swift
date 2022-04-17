//
//  PopUpView.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/08.
//

import UIKit

class PopUpView: UIView {
    
    private let containerView: UIView = {
        let view = UIView()
        view.backgroundColor = .white
        view.layer.cornerRadius = 8
        return view
    }()
    
    private let containerHeadLineLabel: UILabel = {
        let label = UILabel()
        label.text = Constant.PopUpViewText.headLineLabel
        label.font = UIFont(name: Constant.Font.gothicNeoBold, size: 16)
        return label
    }()
    
    private(set) var containerTitleTextField: UITextField = {
        let textField = UITextField()
        textField.attributedPlaceholder = NSAttributedString(
            string: Constant.PopUpViewText.titleTextFieldPlaceholder,
            attributes: [.font: UIFont(name: Constant.Font.gothicNeoBold, size: 14) as Any,
                         .foregroundColor: UIColor(red: 130/255, green: 130/255, blue: 130/255, alpha: 1.0)]
        )
        return textField
    }()
    
    private(set) var containerContentsTextView: UITextView = {
        let textView = UITextView()
        textView.isScrollEnabled = false
        textView.textContainerInset = .zero
        textView.textContainer.lineFragmentPadding = 0.0
        return textView
    }()
    
    private let buttonStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.distribution = .fillEqually
        stackView.spacing = 8
        return stackView
    }()
    
    private(set) var cancelButton: UIButton = {
        let button = UIButton()
        button.setTitle(Constant.PopUpViewText.cancelButton, for: .normal)
        button.setTitleColor(UIColor.black, for: .normal)
        button.titleLabel?.font = UIFont(name: Constant.Font.gothicNeo, size: 14)
        button.backgroundColor = UIColor(red: 230/255, green: 230/255, blue: 230/255, alpha: 1)
        button.layer.cornerRadius = 5
        return button
    }()
    
    private(set) var submitButton: UIButton = {
        let button = UIButton()
        button.setTitle(Constant.PopUpViewText.submitButton, for: .normal)
        button.titleLabel?.font = UIFont(name: Constant.Font.gothicNeoBold, size: 14)
        button.backgroundColor = .init(red: 0, green: 117/255, blue: 222/225, alpha: 1)
        button.layer.cornerRadius = 5
        return button
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setUpView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUpView()
    }
    
    private func setUpView() {
        configureView()
        
        addSubview(containerView)
        
        containerView.addSubview(containerHeadLineLabel)
        containerView.addSubview(containerTitleTextField)
        containerView.addSubview(containerContentsTextView)
        
        containerView.addSubview(buttonStackView)
        buttonStackView.addArrangedSubview(cancelButton)
        buttonStackView.addArrangedSubview(submitButton)
        
        layoutContainerView()
        layoutContainerHeadLineLabel()
        layoutContainerTitleTextField()
        layoutContainerContentsTextView()
        
        layoutButtonStackView()
        
        resetContentsTextViewPlaceholder()
    }
    
    private func configureView() {
        backgroundColor = .black.withAlphaComponent(0.4)
    }
    
    private func layoutContainerView() {
        containerView.translatesAutoresizingMaskIntoConstraints = false
        
        containerView.topAnchor.constraint(equalTo: topAnchor, constant: 330).isActive = true
        containerView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 397).isActive = true
        containerView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -397).isActive = true
        containerView.bottomAnchor.constraint(equalTo: buttonStackView.bottomAnchor, constant: 16).isActive = true
    }
    
    private func layoutContainerHeadLineLabel() {
        containerHeadLineLabel.translatesAutoresizingMaskIntoConstraints = false
        
        containerHeadLineLabel.topAnchor.constraint(equalTo: containerView.topAnchor, constant: 16).isActive = true
        containerHeadLineLabel.leadingAnchor.constraint(equalTo: containerView.leadingAnchor, constant: 16).isActive = true
        containerHeadLineLabel.trailingAnchor.constraint(equalTo: containerView.trailingAnchor, constant: -16).isActive = true
        containerHeadLineLabel.heightAnchor.constraint(equalToConstant: 23).isActive = true
    }
    
    private func layoutContainerTitleTextField() {
        containerTitleTextField.translatesAutoresizingMaskIntoConstraints = false
        
        containerTitleTextField.topAnchor.constraint(equalTo: containerHeadLineLabel.bottomAnchor, constant: 16).isActive = true
        containerTitleTextField.leadingAnchor.constraint(equalTo: containerHeadLineLabel.leadingAnchor).isActive = true
        containerTitleTextField.trailingAnchor.constraint(equalTo: containerHeadLineLabel.trailingAnchor).isActive = true
    }
    
    private func layoutContainerContentsTextView() {
        containerContentsTextView.translatesAutoresizingMaskIntoConstraints = false
        
        containerContentsTextView.topAnchor.constraint(equalTo: containerTitleTextField.bottomAnchor, constant: 8).isActive = true
        containerContentsTextView.leadingAnchor.constraint(equalTo: containerHeadLineLabel.leadingAnchor).isActive = true
        containerContentsTextView.trailingAnchor.constraint(equalTo: containerHeadLineLabel.trailingAnchor).isActive = true
    }
    
    private func layoutButtonStackView() {
        buttonStackView.translatesAutoresizingMaskIntoConstraints = false
        
        buttonStackView.topAnchor.constraint(equalTo: containerContentsTextView.bottomAnchor, constant: 16).isActive = true
        buttonStackView.trailingAnchor.constraint(equalTo: containerView.trailingAnchor, constant: -16).isActive = true
        buttonStackView.widthAnchor.constraint(equalToConstant: 224).isActive = true
        buttonStackView.heightAnchor.constraint(equalToConstant: 40).isActive = true
    }
}

// MARK: - Functions

extension PopUpView {
    func resetContentsTextViewPlaceholder() {
        containerContentsTextView.text = Constant.PopUpViewText.contentsTextViewPlaceholder
        containerContentsTextView.font = UIFont(name: Constant.Font.gothicNeo, size: 14)
        containerContentsTextView.textColor = UIColor(red: 130/255, green: 130/255, blue: 130/255, alpha: 1.0)
    }
    
    func getTitleTextFieldText() -> String? {
        return containerTitleTextField.text
    }
    
    func getContentsTextViewText() -> String {
        return containerContentsTextView.text
    }
}
