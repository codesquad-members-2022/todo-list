//
//  CardEditPopupController.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import UIKit
import Combine

protocol CardEditPopupBinding {
    var confimPublisher: AnyPublisher<(String, String), Never> { get }
}

class CardEditPopupController: UIViewController, CardEditPopupBinding {
    enum Constants {
        static let titlePlaceHolder = "제목을 입력하세요"
        static let bodyPlaceHolder = "내용을 입력하세요"
        static let placeHolderColor = UIColor.gray3
        static let maxBodyHeight = 300.0
        static let maxBodyLength = 100
    }
    
    private let popupBackground: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.backgroundColor = .white
        view.layer.cornerRadius = 6
        view.layer.borderColor = UIColor.blue.cgColor
        view.layer.borderWidth = 1
        return view
    }()
    
    private let statusLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = .systemFont(ofSize: 16, weight: .bold)
        label.textColor = .black
        label.textAlignment = .left
        label.numberOfLines = 1
        label.text = "새로운 카드 추가"
        return label
    }()
    
    private let titleTextField: UITextField = {
        let textField = UITextField()
        textField.translatesAutoresizingMaskIntoConstraints = false
        textField.font = .systemFont(ofSize: 14, weight: .bold)
        textField.textColor = .black
        textField.attributedPlaceholder = NSAttributedString(string: Constants.titlePlaceHolder, attributes: [NSAttributedString.Key.foregroundColor : UIColor.gray3])
        textField.textAlignment = .left
        return textField
    }()
    
    private let bodyTextView: UITextView = {
        let textView = PlaceholderTextView()
        textView.translatesAutoresizingMaskIntoConstraints = false
        textView.font = .systemFont(ofSize: 14, weight: .regular)
        textView.textColor = .black
        textView.textAlignment = .left
        textView.placeholder = Constants.bodyPlaceHolder
        textView.isScrollEnabled = false
        return textView
    }()
    
    private let maxBodyLength: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "(0/\(Constants.maxBodyLength))"
        label.font = .systemFont(ofSize: 14)
        label.textAlignment = .right
        label.textColor = .gray3
        return label
    }()
    
    private let cancel: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("취소", for: .normal)
        button.titleLabel?.font = .systemFont(ofSize: 14, weight: .regular)
        button.setTitleColor(.gray3, for: .normal)
        button.backgroundColor = .gray5
        button.layer.cornerRadius = 6
        return button
    }()
    
    private let confim: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("등록", for: .normal)
        button.titleLabel?.font = .systemFont(ofSize: 14, weight: .bold)
        button.setTitleColor(.white, for: .normal)
        button.setTitleColor(.white.withAlphaComponent(0.4), for: .disabled)
        button.backgroundColor = .blue
        button.layer.cornerRadius = 6
        button.isEnabled = false
        return button
    }()
    
    private var cancellables = Set<AnyCancellable>()
    
    var confimPublisher: AnyPublisher<(String, String), Never> {
        self.confim.publisher(for: .touchUpInside)
            .map { _ -> (String, String) in
                let titleText = self.titleTextField.text ?? ""
                let bodyText = self.bodyTextView.text ?? ""
                return (titleText, bodyText)
            }.eraseToAnyPublisher()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bind()
        attribute()
        layout()
    }
    
    private func bind() {
        self.bodyTextView.delegate = self
        self.bodyTextView.changePublisher()
            .sink { textView in
                let size = CGSize(width: textView.frame.width, height: .infinity)
                let estimatedSize = textView.sizeThatFits(size)
                textView.constraints.forEach { constraint in
                    if constraint.firstAttribute == .height {
                        constraint.constant = estimatedSize.height > Constants.maxBodyHeight ? Constants.maxBodyHeight : estimatedSize.height
                        textView.isScrollEnabled = estimatedSize.height > Constants.maxBodyHeight
                    }
                }
            }.store(in: &cancellables)
        
        Publishers
            .Merge(
                self.titleTextField.changedPublisher().map { _ in },
                self.bodyTextView.changePublisher().map { _ in }
            )
            .map { _ -> Bool in
                let titleText = self.titleTextField.text ?? ""
                let bodyText = self.bodyTextView.text ?? ""
                return !titleText.isEmpty && !bodyText.isEmpty
            }
            .sink{ isEnable in
                self.confim.isEnabled = isEnable
            }.store(in: &cancellables)
        
        Publishers
            .Merge(
                cancel.publisher(for: .touchUpInside),
                confim.publisher(for: .touchUpInside)
            )
            .sink {
                self.dismiss(animated: false)
            }.store(in: &cancellables)
    }
    
    private func attribute() {
        self.view.backgroundColor = .black.withAlphaComponent(0.4)
    }
    
    private func layout() {
        self.view.addSubview(popupBackground)
        [
            popupBackground.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            popupBackground.centerYAnchor.constraint(equalTo: self.view.centerYAnchor),
            popupBackground.widthAnchor.constraint(equalToConstant: 400)
        ].forEach{ $0.isActive = true}
        
        popupBackground.addSubview(statusLabel)
        [
            statusLabel.topAnchor.constraint(equalTo: popupBackground.topAnchor, constant: 16),
            statusLabel.leadingAnchor.constraint(equalTo: popupBackground.leadingAnchor, constant: 16),
            statusLabel.trailingAnchor.constraint(equalTo: popupBackground.trailingAnchor, constant: -16)
        ].forEach{ $0.isActive = true}
        
        popupBackground.addSubview(titleTextField)
        [
            titleTextField.topAnchor.constraint(equalTo: statusLabel.bottomAnchor, constant: 16),
            titleTextField.leadingAnchor.constraint(equalTo: popupBackground.leadingAnchor, constant: 16),
            titleTextField.trailingAnchor.constraint(equalTo: popupBackground.trailingAnchor, constant: -16)
        ].forEach{ $0.isActive = true}

        popupBackground.addSubview(bodyTextView)
        [
            bodyTextView.topAnchor.constraint(equalTo: titleTextField.bottomAnchor, constant: 8),
            bodyTextView.leadingAnchor.constraint(equalTo: popupBackground.leadingAnchor, constant: 16),
            bodyTextView.trailingAnchor.constraint(equalTo: popupBackground.trailingAnchor, constant: -16),
            bodyTextView.heightAnchor.constraint(equalToConstant: 40)
        ].forEach{ $0.isActive = true}
        
        popupBackground.addSubview(maxBodyLength)
        [
            maxBodyLength.topAnchor.constraint(equalTo: bodyTextView.bottomAnchor, constant: 8),
            maxBodyLength.leadingAnchor.constraint(equalTo: popupBackground.leadingAnchor, constant: 16),
            maxBodyLength.trailingAnchor.constraint(equalTo: popupBackground.trailingAnchor, constant: -16),
        ].forEach{ $0.isActive = true}
        
        popupBackground.addSubview(confim)
        [
            confim.topAnchor.constraint(equalTo: maxBodyLength.bottomAnchor, constant: 16),
            confim.rightAnchor.constraint(equalTo: popupBackground.rightAnchor, constant: -16),
            confim.widthAnchor.constraint(equalToConstant: 108),
            confim.heightAnchor.constraint(equalToConstant: 40)
        ].forEach{ $0.isActive = true}

        popupBackground.addSubview(cancel)
        [
            cancel.topAnchor.constraint(equalTo: confim.topAnchor),
            cancel.rightAnchor.constraint(equalTo: confim.leftAnchor, constant: -8),
            cancel.widthAnchor.constraint(equalToConstant: 108),
            cancel.heightAnchor.constraint(equalToConstant: 40)
        ].forEach{ $0.isActive = true}
          
        popupBackground.bottomAnchor.constraint(equalTo: confim.bottomAnchor, constant: 16).isActive = true
    }
}

extension CardEditPopupController: UITextViewDelegate {
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        let currentText = textView.text ?? ""
        guard let stringRange = Range(range, in: currentText) else { return false }
        
        let changedText = currentText.replacingCharacters(in: stringRange, with: text)
        maxBodyLength.text = "(\(changedText.count)/\(Constants.maxBodyLength))  "
        return changedText.count < Constants.maxBodyLength
    }
}
