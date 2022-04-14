//
//  Alert.swift
//  TodoApp
//
//  Created by YEONGJIN JANG on 2022/04/14.
//

import Foundation
import UIKit

struct Alert {
    static func showNetworkAlert(on vc: UIViewController, with title: String, message: String) {
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "확인", style: .default, handler: nil))
        vc.present(alert, animated: true)
    }
}
