//
//  MainViewController.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/05.
//

import UIKit

class MainViewController: UIViewController {
    
    private let titleView = TitleView()
    private let toDoViewController = ToDoViewController()
    private let inProgressViewController = InProgressViewController()
    private let doneViewController = DoneViewController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpView()
    }
    
    private func setUpView() {
        view.backgroundColor = .systemGray6
        
        guard let toDoView = toDoViewController.view else { return }
        guard let inProgressView = inProgressViewController.view else { return }
        guard let doneView = doneViewController.view else { return }
        
        view.addSubview(titleView)
        view.addSubview(toDoView)
        view.addSubview(inProgressView)
        view.addSubview(doneView)
        
        titleView.translatesAutoresizingMaskIntoConstraints = false
        toDoView.translatesAutoresizingMaskIntoConstraints = false
        inProgressView.translatesAutoresizingMaskIntoConstraints = false
        doneView.translatesAutoresizingMaskIntoConstraints = false
        
        titleView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor).isActive = true
        titleView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        titleView.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
        titleView.heightAnchor.constraint(equalToConstant: 72).isActive = true
        
        toDoView.topAnchor.constraint(equalTo: titleView.topAnchor, constant: 100).isActive = true
        toDoView.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant: -50).isActive = true
        toDoView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 48).isActive = true
        toDoView.widthAnchor.constraint(equalToConstant: 258).isActive = true
        
        inProgressView.topAnchor.constraint(equalTo: toDoView.topAnchor).isActive = true
        inProgressView.bottomAnchor.constraint(equalTo: toDoView.bottomAnchor).isActive = true
        inProgressView.leadingAnchor.constraint(equalTo: toDoView.trailingAnchor, constant: 22).isActive = true
        inProgressView.widthAnchor.constraint(equalToConstant: 258).isActive = true
        
        doneView.topAnchor.constraint(equalTo: toDoView.topAnchor).isActive = true
        doneView.bottomAnchor.constraint(equalTo: toDoView.bottomAnchor).isActive = true
        doneView.leadingAnchor.constraint(equalTo: inProgressView.trailingAnchor, constant: 22).isActive = true
        doneView.widthAnchor.constraint(equalToConstant: 258).isActive = true
    }
}
