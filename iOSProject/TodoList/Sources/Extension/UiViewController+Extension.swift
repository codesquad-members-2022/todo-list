//
//  UiViewController+Extension.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import UIKit

extension UIViewController {
    func embed(_ viewController: UIViewController) {
        self.addChild(viewController)
        self.view.addSubview(viewController.view)
        viewController.didMove(toParent: self)
    }
}
