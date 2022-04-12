//
//  NewCardViewController.swift
//  TodoList
//
//  Created by Bibi on 2022/04/12.
//

import UIKit

class NewCardViewController: UIViewController {

    private let newCardView = NewCardView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view = newCardView
        // newCardView.setViewConstraint()
        newCardView.setUIConstraint()
        // Do any additional setup after loading the view.
    }
    
    // 등록 버튼 터치 NewCardVC에서 확인하기 - 델리게이트로.
}
