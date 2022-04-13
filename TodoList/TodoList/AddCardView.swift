//
//  AddCardView.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/12.
//

import UIKit

final class AddCardView: UIView {
    private var msgLabel: UILabel!
    private var titleTextField: UITextField!
    private var bodyTextField: UITextField!
    private var cancelButton: UIButton!
    private var confirmButton: UIButton!
    
    override init(frame: CGRect){
        super.init(frame: frame)
        setAttributes()
    }
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setAttributes()
    }
}

private extension AddCardView{
    func setAttributes(){
        configureMsgLabel()
        configureTitleTextField()
        configureBodyTextField()
        configureCancelButton()
        configureConfirmButton()
        self.backgroundColor = .white
        self.layer.borderWidth = 1
        self.layer.borderColor = UIColor(red: 0, green: 0.459, blue: 0.871, alpha: 1).cgColor
        self.layer.cornerRadius = 6
    }
    
    func configureMsgLabel(){
        msgLabel = UILabel()
        msgLabel.text = "새로운 카드 추가"
        msgLabel.font = UIFont(name: "NotoSansKR-Regular", size: 16)
        self.addSubview(msgLabel)
        
        msgLabel.translatesAutoresizingMaskIntoConstraints = false
        msgLabel.topAnchor.constraint(equalTo: self.topAnchor, constant: 16).isActive = true
        msgLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16).isActive = true
        msgLabel.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: 16).isActive = true
        msgLabel.heightAnchor.constraint(equalToConstant: 23).isActive = true
        msgLabel.widthAnchor.constraint(equalToConstant: 368).isActive = true
    }
    
    func configureTitleTextField(){
        titleTextField = UITextField()
        titleTextField.font = UIFont(name: "NotoSansKR-Bold", size: 16)
        titleTextField.placeholder = "제목을 입력하세요"
        self.addSubview(titleTextField)
        
        titleTextField.translatesAutoresizingMaskIntoConstraints = false
        titleTextField.topAnchor.constraint(equalTo: msgLabel.bottomAnchor, constant: 16).isActive = true
        titleTextField.leadingAnchor.constraint(equalTo: msgLabel.leadingAnchor).isActive = true
        titleTextField.trailingAnchor.constraint(equalTo: msgLabel.trailingAnchor).isActive = true
    }
    
    func configureBodyTextField(){
        bodyTextField = UITextField()
        bodyTextField.font = UIFont(name: "NotoSansKR-Regular", size: 14)
        bodyTextField.placeholder = "내용을 입력하세요"
        self.addSubview(bodyTextField)
        
        bodyTextField.translatesAutoresizingMaskIntoConstraints = false
        bodyTextField.topAnchor.constraint(equalTo: titleTextField.bottomAnchor, constant: 8).isActive = true
        bodyTextField.leadingAnchor.constraint(equalTo: titleTextField.leadingAnchor).isActive = true
        bodyTextField.trailingAnchor.constraint(equalTo: titleTextField.trailingAnchor).isActive = true
        bodyTextField.heightAnchor.constraint(equalToConstant: 20).isActive = true
    }
    
    func configureCancelButton(){
        cancelButton = UIButton()
        cancelButton.setTitle("취소", for: .normal)
        cancelButton.backgroundColor = UIColor(red: 0.878, green: 0.878, blue: 0.878, alpha: 1)
        cancelButton.tintColor = UIColor(red: 0.51, green: 0.51, blue: 0.51, alpha: 1)
        cancelButton.layer.cornerRadius = 6
        self.addSubview(cancelButton)
        
        cancelButton.translatesAutoresizingMaskIntoConstraints = false
        cancelButton.topAnchor.constraint(equalTo: bodyTextField.bottomAnchor, constant: 16).isActive = true
        cancelButton.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 160).isActive = true
        cancelButton.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -16).isActive = true
        cancelButton.widthAnchor.constraint(equalToConstant: 108).isActive = true
        cancelButton.heightAnchor.constraint(equalToConstant: 40).isActive = true
    }
    
    func configureConfirmButton(){
        confirmButton = UIButton()
        confirmButton.setTitle("확인", for: .normal)
        confirmButton.backgroundColor = UIColor(red: 0.525, green: 0.775, blue: 1, alpha: 1)
        confirmButton.tintColor = UIColor(red: 1, green: 1, blue: 1, alpha: 0.4)
        confirmButton.layer.cornerRadius = 6
        self.addSubview(confirmButton)
        
        confirmButton.translatesAutoresizingMaskIntoConstraints = false
        confirmButton.topAnchor.constraint(equalTo: cancelButton.topAnchor).isActive = true
        confirmButton.leadingAnchor.constraint(equalTo: cancelButton.trailingAnchor, constant: 8).isActive = true
        confirmButton.bottomAnchor.constraint(equalTo: cancelButton.bottomAnchor).isActive = true
        confirmButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16).isActive = true
        confirmButton.widthAnchor.constraint(equalToConstant: 108).isActive = true
        confirmButton.heightAnchor.constraint(equalToConstant: 40).isActive = true
    }
}
