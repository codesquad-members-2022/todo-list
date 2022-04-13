//
//  NewCardViewController.swift
//  TodoList
//
//  Created by Bibi on 2022/04/12.
//

import UIKit

class NewCardViewController: UIViewController {

    private lazy var newCardView = NewCardView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
//        let newCardView = NewCardView()
        view.addSubview(newCardView)
        newCardView.setViewConstraint()
        newCardView.setUI()
    }
    
    // 등록 버튼 터치 NewCardVC에서 확인하기 - 델리게이트로.
}
