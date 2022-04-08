//
//  PopUpView.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/08.
//

import UIKit

class PopUpView: UIView {
    
    private let popUpBackgroundView: UIView = {
        let view = UIView()
        view.backgroundColor = .white
        view.layer.cornerRadius = 8
        return view
    }()
    
    private let popUpStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .vertical
        stackView.spacing = 8
        stackView.alignment = .leading
        stackView.distribution = .equalSpacing
        return stackView
    }()
    
    private let popUpHeadLineLabel: UILabel = {
        let label = UILabel()
        label.text = "새로운 카드 추가"
        label.font = UIFont(name: "Apple SD Gothic Neo Bold", size: 18)
        return label
    }()
    
    private let popUpTitleLabel: UILabel = {
        let label = UILabel()
        label.text = "제목을 입력하세요."
        label.font = UIFont(name: "Apple SD Gothic Neo Bold", size: 15)
        label.textColor = .darkGray
        return label
    }()
    
    private let popUpContentsLabel: UILabel = {
        let label = UILabel()
        label.text = "내용을 입력하세요."
        label.font = UIFont(name: "Apple SD Gothic Neo", size: 15)
        label.textColor = .darkGray
        return label
    }()
    
    private let cancelButton: UIButton = {
        let button = UIButton()
        button.setTitle("취소", for: .normal)
        button.setTitleColor(UIColor.black, for: .normal)
        button.titleLabel?.font = UIFont(name: "Apple SD Gothic Neo", size: 14)
        button.backgroundColor = UIColor(red: 230/255, green: 230/255, blue: 230/255, alpha: 1)
        button.layer.cornerRadius = 5
        return button
    }()
    
    private let submitButton: UIButton = {
        let button = UIButton()
        button.setTitle("등록", for: .normal)
        button.titleLabel?.font = UIFont(name: "Apple SD Gothic Neo", size: 14)
        button.backgroundColor = .systemBlue
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
        addSubview(popUpBackgroundView)
        
        popUpBackgroundView.addSubview(popUpHeadLineLabel)
        popUpBackgroundView.addSubview(popUpStackView)
        popUpBackgroundView.addSubview(cancelButton)
        popUpBackgroundView.addSubview(submitButton)
        
        popUpStackView.addArrangedSubview(popUpTitleLabel)
        popUpStackView.addArrangedSubview(popUpContentsLabel)
        
        layoutPopUpBackgroundView()
        layoutPopUpHeadLineLabel()
        layoutPopUpStackView()
        layoutCancelButton()
        layoutSubmitButton()
    }
    
    private func configureView() {
        backgroundColor = UIColor(red: 0/255, green: 0/255, blue: 0/255, alpha: 0.6)
    }
    
    private func layoutPopUpBackgroundView() {
        popUpBackgroundView.translatesAutoresizingMaskIntoConstraints = false
        
        popUpBackgroundView.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
        popUpBackgroundView.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        popUpBackgroundView.widthAnchor.constraint(equalToConstant: 400).isActive = true
        popUpBackgroundView.heightAnchor.constraint(equalToConstant: 175).isActive = true
    }
    
    private func layoutPopUpHeadLineLabel() {
        popUpHeadLineLabel.translatesAutoresizingMaskIntoConstraints = false
        
        popUpHeadLineLabel.topAnchor.constraint(equalTo: popUpBackgroundView.topAnchor, constant: 16).isActive = true
        popUpHeadLineLabel.bottomAnchor.constraint(equalTo: popUpBackgroundView.bottomAnchor, constant: -136).isActive = true
        popUpHeadLineLabel.leadingAnchor.constraint(equalTo: popUpBackgroundView.leadingAnchor, constant: 16).isActive = true
        popUpHeadLineLabel.trailingAnchor.constraint(equalTo: popUpBackgroundView.trailingAnchor, constant: -16).isActive = true
    }
    
    private func layoutPopUpStackView() {
        popUpStackView.translatesAutoresizingMaskIntoConstraints = false
        
        popUpStackView.topAnchor.constraint(equalTo: popUpBackgroundView.topAnchor, constant: 60).isActive = true
        popUpStackView.bottomAnchor.constraint(equalTo: popUpBackgroundView.bottomAnchor, constant: -72).isActive = true
        popUpStackView.leadingAnchor.constraint(equalTo: popUpBackgroundView.leadingAnchor, constant: 16).isActive = true
        popUpStackView.trailingAnchor.constraint(equalTo: popUpBackgroundView.trailingAnchor, constant: -16).isActive = true
    }
    
    private func layoutCancelButton() {
        cancelButton.translatesAutoresizingMaskIntoConstraints = false
        
        cancelButton.topAnchor.constraint(equalTo: popUpBackgroundView.topAnchor, constant: 119).isActive = true
        cancelButton.bottomAnchor.constraint(equalTo: popUpBackgroundView.bottomAnchor, constant: -16).isActive = true
        cancelButton.leadingAnchor.constraint(equalTo: popUpBackgroundView.leadingAnchor, constant: 160).isActive = true
        cancelButton.trailingAnchor.constraint(equalTo: popUpBackgroundView.trailingAnchor, constant: -132).isActive = true
    }
    
    private func layoutSubmitButton() {
        submitButton.translatesAutoresizingMaskIntoConstraints = false
        
        submitButton.topAnchor.constraint(equalTo: popUpBackgroundView.topAnchor, constant: 119).isActive = true
        submitButton.bottomAnchor.constraint(equalTo: popUpBackgroundView.bottomAnchor, constant: -16).isActive = true
        submitButton.leadingAnchor.constraint(equalTo: popUpBackgroundView.leadingAnchor, constant: 276).isActive = true
        submitButton.trailingAnchor.constraint(equalTo: popUpBackgroundView.trailingAnchor, constant: -16).isActive = true
    }
}
