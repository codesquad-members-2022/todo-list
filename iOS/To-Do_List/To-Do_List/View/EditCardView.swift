//
//  EditCardView.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/11.
//

import UIKit

class EditCardView:UIView {
    
    private lazy var headLineLabel:UILabel = {
        let label = UILabel()
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
        return button
    }()
    
    private lazy var confirmButton:UIButton = {
        let button = UIButton()
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
    
    func setHeadLineLabel(editStyle:EditStyle) {
        self.headLineLabel.text = editStyle.description
    }
    
    
    private func addViews() {
        [headLineLabel,headLineInputTextField,contentInputTextField,cancelButton,confirmButton].forEach {
            $0.translatesAutoresizingMaskIntoConstraints = false
            self.addSubview($0)
        }
    }
    
    private func setup() {
        let inset:CGFloat = 8.0
        NSLayoutConstraint.activate([
            headLineLabel.topAnchor.constraint(equalTo: self.topAnchor, constant: inset * 2),
            headLineLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: inset),
            
            headLineInputTextField.topAnchor.constraint(equalTo: self.headLineLabel.bottomAnchor, constant: inset * 2),
            headLineInputTextField.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: inset),
            
            contentInputTextField.topAnchor.constraint(equalTo: self.headLineInputTextField.bottomAnchor, constant: inset),
            contentInputTextField.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: inset),
            
            confirmButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -inset),
            confirmButton.topAnchor.constraint(equalTo: self.contentInputTextField.topAnchor, constant: inset),
            
            cancelButton.trailingAnchor.constraint(equalTo: self.confirmButton.leadingAnchor, constant: -inset),
            cancelButton.topAnchor.constraint(equalTo: self.contentInputTextField.bottomAnchor, constant: inset),
            
        ])
    
    }
    
}

enum EditStyle:CustomStringConvertible {
    var description: String {
        switch self {
        case .add:
            return "새로운 카드 추가"
        case .editContent:
            return "카드 수정"
        }
    }
    case add
    case editContent
}
