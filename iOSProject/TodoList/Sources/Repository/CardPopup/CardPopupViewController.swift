//
//  CardEditPopupController.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import UIKit
import Combine

protocol CardPopupViewDeletegate {
    func cardPopupView(_ cardPopupView: CardPopupViewController, editedCard: Card)
    func cardPopupView(_ cardPopupView: CardPopupViewController, addedCard: Card)
}

class CardPopupViewController: UIViewController {
    enum Constants {
        static let newCardStatusLabel = "새로운 카드 추가"
        static let editCardStatusLabel = "카드수정"
        static let titlePlaceHolder = "제목을 입력하세요"
        static let bodyPlaceHolder = "내용을 입력하세요"
        static let placeHolderColor = UIColor.gray3
        static let maxBodyHeight = 300.0
        static let maxtitleLength = 50
        static let maxBodyLength = 500
    }
    
    private let popupBackgroundView: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.backgroundColor = .white
        view.layer.cornerRadius = 6
        view.layer.borderColor = UIColor.blue.cgColor
        view.layer.borderWidth = 1
        return view
    }()
    
    private let columnLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = .systemFont(ofSize: 16, weight: .bold)
        label.textColor = .black
        label.textAlignment = .left
        label.numberOfLines = 1
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
    
    private let maxBodyLengthLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "(0/\(Constants.maxBodyLength))"
        label.font = .systemFont(ofSize: 14)
        label.textAlignment = .right
        label.textColor = .gray3
        return label
    }()
    
    private let cancelButton: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("취소", for: .normal)
        button.titleLabel?.font = .systemFont(ofSize: 14, weight: .regular)
        button.setTitleColor(.gray3, for: .normal)
        button.backgroundColor = .gray5
        button.layer.cornerRadius = 6
        return button
    }()
    
    private let confimButton: UIButton = {
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
    
    private let editButton: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("수정", for: .normal)
        button.titleLabel?.font = .systemFont(ofSize: 14, weight: .bold)
        button.setTitleColor(.white, for: .normal)
        button.setTitleColor(.white.withAlphaComponent(0.4), for: .disabled)
        button.backgroundColor = .blue
        button.layer.cornerRadius = 6
        button.isEnabled = false
        return button
    }()
    
    private var cancellables = Set<AnyCancellable>()
    private let model: CardPopupViewModelBinding = CardPopupViewModel()
    
    private var baseTitleText: String = ""
    private var baseBodyText: String = ""
    private var cardIndex: Int?
    
    var delegate: CardPopupViewDeletegate?
    
    init(card: Card? = nil) {
        super.init(nibName: nil, bundle: nil)
        initialize(card: card)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        initialize(card: nil)
    }
    
    private func initialize(card: Card?) {
        self.baseTitleText = card?.title ?? ""
        self.baseBodyText = card?.title ?? ""
        self.cardIndex = card?.orderIndex
        
        self.columnLabel.text = card == nil ? Constants.newCardStatusLabel : Constants.editCardStatusLabel
        self.titleTextField.text = self.baseTitleText
        self.bodyTextView.text = self.baseBodyText
        self.maxBodyLengthLabel.text = "(\(self.bodyTextView.text.count)/\(Constants.maxBodyLength))"
        self.confimButton.isHidden = card != nil
        self.editButton.isHidden = card == nil
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bind()
        attribute()
        layout()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        NotificationCenter.default.post(name: UITextView.textDidChangeNotification, object: self.bodyTextView)
    }
    
    private func bind() {
        self.titleTextField.delegate = self
        self.bodyTextView.delegate = self
        self.bodyTextView.changePublisher()
            .sink(receiveValue: self.reSizeTextView)
            .store(in: &cancellables)
        
        Publishers
            .Merge(
                self.titleTextField.changedPublisher().map { _ in },
                self.bodyTextView.changePublisher().map { _ in }
            )
            .map { _ -> Bool in
                let titleText = self.titleTextField.text ?? ""
                let bodyText = self.bodyTextView.text ?? ""
                
                let equalBaseText = self.baseTitleText == titleText && self.baseBodyText == bodyText
                let isEmpty = titleText.isEmpty || bodyText.isEmpty
                return (!equalBaseText && !isEmpty)
            }
            .sink{ isEnable in
                self.confimButton.isEnabled = isEnable
                self.editButton.isEnabled = isEnable
            }.store(in: &cancellables)
        
        Publishers
            .Merge(
                cancelButton.publisher(for: .touchUpInside),
                confimButton.publisher(for: .touchUpInside)
            )
            .sink {
                self.dismiss(animated: false)
            }.store(in: &cancellables)
        
        confimButton.publisher(for: .touchUpInside)
            .sink {
                guard let titleText = self.titleTextField.text,
                      let bodyText = self.bodyTextView.text else {
                    return
                }
                self.model.action.addCard.send((titleText, bodyText))
            }.store(in: &cancellables)
        
        self.model.state.addedCard
            .sink {
                self.delegate?.cardPopupView(self, addedCard: $0)
                self.dismiss(animated: false)
            }.store(in: &cancellables)
        
        editButton.publisher(for: .touchUpInside)
            .sink {
                guard let titleText = self.titleTextField.text,
                      let bodyText = self.bodyTextView.text,
                      let cardIndex = self.cardIndex else {
                    return
                }
                self.model.action.editCard.send((cardIndex, titleText, bodyText))
            }.store(in: &cancellables)
        
        self.model.state.editedCard
            .sink {
                self.delegate?.cardPopupView(self, editedCard: $0)
                self.dismiss(animated: false)
            }.store(in: &cancellables)
    }
    
    private func attribute() {
        self.view.backgroundColor = .black.withAlphaComponent(0.4)
    }
    
    private func layout() {
        self.view.addSubview(popupBackgroundView)
        [
            popupBackgroundView.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            popupBackgroundView.centerYAnchor.constraint(equalTo: self.view.centerYAnchor),
            popupBackgroundView.widthAnchor.constraint(equalToConstant: 400)
        ].forEach{ $0.isActive = true}
        
        popupBackgroundView.addSubview(columnLabel)
        [
            columnLabel.topAnchor.constraint(equalTo: popupBackgroundView.topAnchor, constant: 16),
            columnLabel.leadingAnchor.constraint(equalTo: popupBackgroundView.leadingAnchor, constant: 16),
            columnLabel.trailingAnchor.constraint(equalTo: popupBackgroundView.trailingAnchor, constant: -16)
        ].forEach{ $0.isActive = true}
        
        popupBackgroundView.addSubview(titleTextField)
        [
            titleTextField.topAnchor.constraint(equalTo: columnLabel.bottomAnchor, constant: 16),
            titleTextField.leadingAnchor.constraint(equalTo: popupBackgroundView.leadingAnchor, constant: 16),
            titleTextField.trailingAnchor.constraint(equalTo: popupBackgroundView.trailingAnchor, constant: -16)
        ].forEach{ $0.isActive = true}

        popupBackgroundView.addSubview(bodyTextView)
        [
            bodyTextView.topAnchor.constraint(equalTo: titleTextField.bottomAnchor, constant: 8),
            bodyTextView.leadingAnchor.constraint(equalTo: popupBackgroundView.leadingAnchor, constant: 16),
            bodyTextView.trailingAnchor.constraint(equalTo: popupBackgroundView.trailingAnchor, constant: -16),
            bodyTextView.heightAnchor.constraint(equalToConstant: 40)
        ].forEach{ $0.isActive = true}
        
        popupBackgroundView.addSubview(maxBodyLengthLabel)
        [
            maxBodyLengthLabel.topAnchor.constraint(equalTo: bodyTextView.bottomAnchor, constant: 8),
            maxBodyLengthLabel.leadingAnchor.constraint(equalTo: popupBackgroundView.leadingAnchor, constant: 16),
            maxBodyLengthLabel.trailingAnchor.constraint(equalTo: popupBackgroundView.trailingAnchor, constant: -16),
        ].forEach{ $0.isActive = true}
        
        popupBackgroundView.addSubview(editButton)
        [
            editButton.topAnchor.constraint(equalTo: maxBodyLengthLabel.bottomAnchor, constant: 16),
            editButton.rightAnchor.constraint(equalTo: popupBackgroundView.rightAnchor, constant: -16),
            editButton.widthAnchor.constraint(equalToConstant: 108),
            editButton.heightAnchor.constraint(equalToConstant: 40)
        ].forEach{ $0.isActive = true}
        
        popupBackgroundView.addSubview(confimButton)
        [
            confimButton.topAnchor.constraint(equalTo: maxBodyLengthLabel.bottomAnchor, constant: 16),
            confimButton.rightAnchor.constraint(equalTo: popupBackgroundView.rightAnchor, constant: -16),
            confimButton.widthAnchor.constraint(equalToConstant: 108),
            confimButton.heightAnchor.constraint(equalToConstant: 40)
        ].forEach{ $0.isActive = true}

        popupBackgroundView.addSubview(cancelButton)
        [
            cancelButton.topAnchor.constraint(equalTo: confimButton.topAnchor),
            cancelButton.rightAnchor.constraint(equalTo: confimButton.leftAnchor, constant: -8),
            cancelButton.widthAnchor.constraint(equalToConstant: 108),
            cancelButton.heightAnchor.constraint(equalToConstant: 40)
        ].forEach{ $0.isActive = true}
          
        popupBackgroundView.bottomAnchor.constraint(equalTo: confimButton.bottomAnchor, constant: 16).isActive = true
    }
    
    private func reSizeTextView(_ textView: UITextView) {
        let size = CGSize(width: textView.frame.width, height: .infinity)
        let estimatedSize = textView.sizeThatFits(size)
        textView.constraints.forEach { constraint in
            if constraint.firstAttribute == .height {
                constraint.constant = estimatedSize.height > Constants.maxBodyHeight ? Constants.maxBodyHeight : estimatedSize.height
                textView.isScrollEnabled = estimatedSize.height > Constants.maxBodyHeight
            }
        }
    }
}

extension CardPopupViewController: UITextViewDelegate {
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        let currentText = textView.text ?? ""
        guard let stringRange = Range(range, in: currentText) else { return false }
        
        let changedText = currentText.replacingCharacters(in: stringRange, with: text)
        maxBodyLengthLabel.text = "(\(changedText.count)/\(Constants.maxBodyLength))  "
        return changedText.count < Constants.maxBodyLength
    }
}

extension CardPopupViewController: UITextFieldDelegate {
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        let currentText = textField.text ?? ""
        guard let stringRange = Range(range, in: currentText) else { return false }
        
        let changedText = currentText.replacingCharacters(in: stringRange, with: string)
        return changedText.count < Constants.maxtitleLength
    }
}
