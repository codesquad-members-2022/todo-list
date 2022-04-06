//
//  UITextView+Extension.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import UIKit
import Combine

extension UITextView {
    func changePublisher() -> AnyPublisher<String, Never> {
        NotificationCenter.default.publisher(for: UITextView.textDidChangeNotification, object: self )
            .map { _ in self.text}
            .eraseToAnyPublisher()
    }
    
    func beginEditingPublisher() -> AnyPublisher<Void, Never> {
        NotificationCenter.default.publisher(for: UITextView.textDidBeginEditingNotification, object: self )
            .map { _ in }
            .eraseToAnyPublisher()
    }
    
    func endEditingPublisher() -> AnyPublisher<Void, Never> {
        NotificationCenter.default.publisher(for: UITextView.textDidBeginEditingNotification, object: self )
            .map { _ in }
            .eraseToAnyPublisher()
    }
}
