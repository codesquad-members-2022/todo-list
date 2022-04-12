//
//  PopUpViewController.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/11.
//

import UIKit

class PopUpViewController: UIViewController {
    
    private let popUpView = PopUpView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.addSubview(popUpView)
        layoutPopUpView()

        popUpView.cancelButton.addTarget(self, action: #selector(didTapCancelButton), for: .touchUpInside)
        popUpView.submitButton.addTarget(self, action: #selector(didTapSubmitButton), for: .touchUpInside)
    }
    
    private func layoutPopUpView() {
        popUpView.translatesAutoresizingMaskIntoConstraints = false
        popUpView.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        popUpView.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        popUpView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        popUpView.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
    }
}

//MARK: - selector functions

extension PopUpViewController {
    @objc func didTapCancelButton() {
        self.dismiss(animated: false, completion: nil)
        self.popUpView.containerTitleTextField.text = ""
        self.popUpView.containerContentsTextField.text = ""
    }
    
    @objc func didTapSubmitButton() {
        //TODO: 등록 동작 구현
        self.dismiss(animated: false, completion: nil)
    }
}
