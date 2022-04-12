//
//  NewCardView.swift
//  TodoList
//
//  Created by Bibi on 2022/04/12.
//

import Foundation
import UIKit

class NewCardView: UIView {
    
    lazy var newCardLabel: UILabel = {
        let label = UILabel()
        label.text = "새로운 카드 추가"
        return label
    }()
    
    lazy var titleTextField: UITextField = {
        let textField = UITextField()
        textField.placeholder = "제목을 입력하세요"
        return textField
    }()
    
    lazy var contentTextView: UITextView = {
        let textView = UITextView()
        // textView.placeholder (없음 - 뷰컨의 UITextViewDelegate에 구현해야 함)
        return textView
    }()
    
    lazy var addButton: UIButton = {
        let button = UIButton()
        button.titleLabel?.text = "등록"
        button.backgroundColor = .systemBlue
        return button
    }()
    
    lazy var cancelButton: UIButton = {
        let button = UIButton()
        button.titleLabel?.text = "취소"
        button.backgroundColor = .systemGray4
        return button
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    func setUIConstraint() {
        setNewCardLabelConstraint()
        setTitleTextFieldConstraint()
        setContentTextViewConstraint()
        setAddButtonConstraint()
        setCancelButtonConstraint()
    }
    
    private func setNewCardLabelConstraint() {
        newCardLabel.translatesAutoresizingMaskIntoConstraints = false
        newCardLabel.topAnchor.constraint(equalTo: self.topAnchor, constant: 15).isActive = true
        newCardLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 15).isActive = true
    }
    
    private func setTitleTextFieldConstraint() {
        titleTextField.translatesAutoresizingMaskIntoConstraints = false
        titleTextField.topAnchor.constraint(equalTo: newCardLabel.bottomAnchor, constant: 15).isActive = true
        titleTextField.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 15).isActive = true
    }
    
    private func setContentTextViewConstraint() {
        contentTextView.translatesAutoresizingMaskIntoConstraints = false
        contentTextView.topAnchor.constraint(equalTo: titleTextField.bottomAnchor, constant: 10).isActive = true
        contentTextView.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 15).isActive = true
    }
    
    private func setAddButtonConstraint() {
        addButton.translatesAutoresizingMaskIntoConstraints = false
        addButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -15).isActive = true
        addButton.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -15).isActive = true
        addButton.widthAnchor.constraint(equalToConstant: CGFloat(100)).isActive = true
        addButton.heightAnchor.constraint(equalToConstant: CGFloat(50)).isActive = true
    }
    
    private func setCancelButtonConstraint() {
        cancelButton.translatesAutoresizingMaskIntoConstraints = false
        cancelButton.trailingAnchor.constraint(equalTo: addButton.leadingAnchor, constant: -5).isActive = true
        cancelButton.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -15).isActive = true
        cancelButton.widthAnchor.constraint(equalToConstant: CGFloat(100)).isActive = true
        cancelButton.heightAnchor.constraint(equalToConstant: CGFloat(50)).isActive = true
    }
}
