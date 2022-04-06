//
//  UITextFieldView+Extension.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import Combine
import UIKit

extension UITextField {
    func changedPublisher() -> AnyPublisher<UITextField, Never> {
        EventPublisher<UITextField>(control: self, event: .editingChanged, receiveClosure: {
            return self
        }).eraseToAnyPublisher()
    }
    
    func addLeftPadding(_ padding: CGFloat) {
        let paddingView = UIView(frame: CGRect(x: 0, y: 0, width: padding, height: self.frame.height))
        self.leftView = paddingView
        self.leftViewMode = .always
    }
}
