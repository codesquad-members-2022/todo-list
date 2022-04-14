//
//  NewCardViewController.swift
//  TodoList
//
//  Created by Bibi on 2022/04/12.
//

import UIKit

class NewCardViewController: UIViewController {

    private lazy var newCardView = NewCardView(frame: .zero)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.addSubview(newCardView)
        newCardView.setUI()
        setViewConstraint()
        newCardView.actionDelegate = self
    }
    
    // 등록 버튼 터치 NewCardVC에서 확인하기 - 델리게이트로.
    
    func setViewConstraint() {
        
        newCardView.translatesAutoresizingMaskIntoConstraints = false
        newCardView.widthAnchor.constraint(equalToConstant: 500).isActive = true
        newCardView.heightAnchor.constraint(equalToConstant: 400).isActive = true
        newCardView.centerXAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.centerXAnchor).isActive = true
        newCardView.centerYAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.centerYAnchor).isActive = true
    }
}

extension NewCardViewController: NewCardViewDelegate {
    func addCardButtonTouched() {
        // NewCardView에서 등록버튼 터치되었을 때 할 작업 대신 처리해주기
    }
}
