//
//  PopUpViewController.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/11.
//

import UIKit
import OSLog

class PopUpViewController: UIViewController {
    
    private let popUpView = PopUpView()
    private let popUpTextViewDelegate = PopUpContentsTextViewDelegate()
    private let networkManager = NetworkManager<CardData>(session: URLSession(configuration: .default))
    
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
        
        guard let title = popUpView.getTitleTextFieldText() else { return }
        let content = popUpView.getContentsTextViewText()
        
        let mockData = CardData(userId: "iOS", title: title, content: content, sequence: 52, status: 1)
        
        networkManager.post(url: ServerAPI.post.url, data: mockData) { (result: Result<CardData, NetworkError>) in
            switch result {
            case .success(let data):
                //TODO: data 처리 로직 구현 (NC 통해 cell에 추가한 뒤 reload)
                break
            case .failure(let error):
                os_log(.error, log: .default, "\(error)")
            }
        }
        
        closePopUpView()
    }
    
    private func closePopUpView() {
        popUpView.resetContentsTextViewPlaceholder()
        dismiss(animated: false, completion: nil)
    }
}

