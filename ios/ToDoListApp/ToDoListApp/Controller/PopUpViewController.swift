//
//  PopUpViewController.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/11.
//

import UIKit

class PopUpViewController: UIViewController {
    
    private let popUpView = PopUpView()
    private let popUpTextViewDelegate = PopUpContentsTextViewDelegate()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpView()
        addTargetActions()
        setUpDelegates()
    }
    
    private func setUpView() {
        view.addSubview(popUpView)
        layoutPopUpView()
    }
    
    private func addTargetActions() {
        popUpView.cancelButton.addTarget(self, action: #selector(didTapCancelButton), for: .touchUpInside)
        popUpView.submitButton.addTarget(self, action: #selector(didTapSubmitButton), for: .touchUpInside)
    }
    
    private func setUpDelegates() {
        popUpView.containerContentsTextView.delegate = popUpTextViewDelegate
    }
}

//MARK: - View Layouts

extension PopUpViewController  {
    private func layoutPopUpView() {
        popUpView.translatesAutoresizingMaskIntoConstraints = false
        popUpView.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        popUpView.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        popUpView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        popUpView.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
    }
}

//MARK: - Selector Functions

extension PopUpViewController {
    @objc func didTapCancelButton() {
        closePopUpView()
    }
    
    @objc func didTapSubmitButton() {
        
        //TODO: - 내용이 없다면 버튼을 비활성화시켜서 이 로직을 애초에 갖지 않게 하기
        guard let title = popUpView.getTitleTextFieldText() else { return }
        let content = popUpView.getContentsTextViewText()
        
        closePopUpView()
    }
    
    private func closePopUpView() {
        popUpView.resetContentsTextViewPlaceholder()
        dismiss(animated: false, completion: nil)
    }
}

