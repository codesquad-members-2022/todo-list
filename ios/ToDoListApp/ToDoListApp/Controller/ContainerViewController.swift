//
//  ContainerViewController.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/08.
//

import UIKit

class ContainerViewController: UIViewController {
    
    enum InspectorState {
        case opened
        case closed
    }
    
    private let mainViewController = MainViewController()
    private let inspectorViewController = InspectorViewController()
    private var inspectorState: InspectorState = .closed
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        mainViewController.delegate = self

        setUpInspectorView()

        addChildViewController(child: mainViewController, parent: self)
        addChildViewController(child: inspectorViewController, parent: self)
    }
    
    private func setUpInspectorView() {
        inspectorViewController.view.frame = CGRect(origin: CGPoint(x: view.frame.width,
                                                                    y: view.frame.origin.y),
                                                    size: CGSize(width: view.frame.width * 0.35,
                                                                 height: view.frame.height))
    }
    
    private func addChildViewController(child: UIViewController, parent: UIViewController) {
        addChild(child)
        child.didMove(toParent: parent)
        view.addSubview(child.view)
    }
}

extension ContainerViewController: MainViewControllerDelegate {
    func didTapInspectorButton() {
        switch inspectorState {
        case .closed:
            UIView.animate(withDuration: 0.5, delay: 0, options: .curveEaseInOut) {
                
                self.inspectorViewController.view.frame = CGRect(origin: CGPoint(x: self.view.frame.width * 0.65,
                                                                                 y: self.view.frame.origin.y),
                                                                 size: CGSize(width: self.view.frame.width * 0.35,
                                                                              height: self.view.frame.height))
            } completion: { [weak self] done in
                self?.inspectorState = .opened
            }
            
        case .opened:
            //TODO: 토글 기능 구현
            break
        }
    }
}
