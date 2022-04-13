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
    }
    
    override func didMove(toParent parent: UIViewController?) {
        super.didMove(toParent: parent)
        newCardView.setUI()
    }
    
    // 등록 버튼 터치 NewCardVC에서 확인하기 - 델리게이트로.
}
