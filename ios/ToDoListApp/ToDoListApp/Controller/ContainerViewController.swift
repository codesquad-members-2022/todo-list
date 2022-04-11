//
//  ContainerViewController.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/08.
//

import UIKit

class ContainerViewController: UIViewController {
    
    private let mainViewController = MainViewController()
    private let inspectorViewController = InspectorViewController()
    private lazy var popUpViewController = PopUpViewController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        addChildViewControllers()
        setUpDelegates()
        
        setUpView()
    }
    
    private func addChildViewControllers() {
        addChildViewController(child: mainViewController, parent: self)
        addChildViewController(child: inspectorViewController, parent: self)
    }
    
    private func addChildViewController(child: UIViewController, parent: UIViewController) {
        addChild(child)
        child.didMove(toParent: parent)
        view.addSubview(child.view)
    }
    
    private func setUpDelegates() {
        mainViewController.delegate = self
        inspectorViewController.delegate = self
        mainViewController.kanbanColumnViewControllers.forEach { kanbanColumnViewController in
            kanbanColumnViewController.delegate = self
        }
    }
}

//MARK: - Set Up View

extension ContainerViewController {
    private func setUpView() {
        setUpInspectorView()
    }
    
    private func setUpInspectorView() {
        inspectorViewController.view.translatesAutoresizingMaskIntoConstraints = false
        
        inspectorViewController.view.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        inspectorViewController.view.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        inspectorViewController.view.leadingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
        inspectorViewController.view.widthAnchor.constraint(equalTo: view.widthAnchor, multiplier: 0.35).isActive = true
    }
}

//MARK: - Delegates

extension ContainerViewController: MainViewControllerDelegate {
    func didTapInspectorOpenButton() {
        UIView.animate(withDuration: 0.5) {
            self.inspectorViewController.view.transform = CGAffineTransform(translationX: -(self.view.frame.width * 0.35), y: 0)
        }
    }
}

extension ContainerViewController: InspectorViewControllerDelegate {
    func didTapInspectorCloseButton() {
        UIView.animate(withDuration: 0.5) {
            self.inspectorViewController.view.transform = .identity
        }
    }
}

extension ContainerViewController: KanbanColumnViewControllerDelegate {
    func didTapAddButton() {
        popUpViewController.modalPresentationStyle = .overFullScreen
        present(popUpViewController, animated: false)
    }
}
