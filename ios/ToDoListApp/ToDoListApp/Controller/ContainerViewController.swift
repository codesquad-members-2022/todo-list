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
    
    override func viewDidLoad() {
        super.viewDidLoad()

        mainViewController.delegate = self
        inspectorViewController.delegate = self

        addChildViewController(child: mainViewController, parent: self)
        addChildViewController(child: inspectorViewController, parent: self)

        setUpInspectorView()
    }
    
    private func setUpInspectorView() {
        inspectorViewController.view.translatesAutoresizingMaskIntoConstraints = false
        
        inspectorViewController.view.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        inspectorViewController.view.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        inspectorViewController.view.leadingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
        inspectorViewController.view.widthAnchor.constraint(equalTo: view.widthAnchor, multiplier: 0.35).isActive = true
    }
    
    private func addChildViewController(child: UIViewController, parent: UIViewController) {
        addChild(child)
        child.didMove(toParent: parent)
        view.addSubview(child.view)
    }
}

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
