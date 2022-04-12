//
//  HistoryViewController.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/12.
//

import Foundation
import UIKit

class HistoryViewController: UIViewController{
    private lazy var historyView = HistoryView(frame: view.frame)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view = historyView
    }
    
}
