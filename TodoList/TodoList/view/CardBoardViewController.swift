//
//  ViewController.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/05.
//

import UIKit

class CardBoardViewController: UIViewController {

    let factory = CardFactory()
    let cardBoard = CardBoard()
    
    @IBOutlet weak var boardStackView: UIStackView!
    @IBOutlet weak var todoContainerView: UIView!
    @IBOutlet weak var doneContainerView: UIView!
    @IBOutlet weak var doingContainerView: UIView!
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemGray5
        let todoStoryBoard = UIStoryboard(name: "TodoStoryboard", bundle: Bundle(for: TodoViewController.self))
        guard let todoViewController = todoStoryBoard.instantiateViewController(withIdentifier: "TodoViewController") as? TodoViewController else {
            return
        }
        
        guard let doingViewController = todoStoryBoard.instantiateViewController(withIdentifier: "TodoViewController") as? TodoViewController else {
            return
        }
        
        guard let doneViewController = todoStoryBoard.instantiateViewController(withIdentifier: "TodoViewController") as? TodoViewController else {
            return
        }
        
        addChild(todoViewController)
        addChild(doingViewController)
        addChild(doneViewController)
        todoContainerView.addSubview(todoViewController.view)
        doingContainerView.addSubview(doingViewController.view)
        doneContainerView.addSubview(doneViewController.view)
        
        todoViewController.setTitleLabel(title: "해야 할 일")
        doingViewController.setTitleLabel(title: "하고 있는 일")
        doneViewController.setTitleLabel(title: "완료한 일")
        
        todoViewController.view.translatesAutoresizingMaskIntoConstraints = false
        todoViewController.view.heightAnchor.constraint(equalTo: todoContainerView.heightAnchor, constant: 0).isActive = true
        todoViewController.view.widthAnchor.constraint(equalTo: todoContainerView.widthAnchor, constant: 0).isActive = true
        todoViewController.view.centerXAnchor.constraint(equalTo: todoContainerView.centerXAnchor, constant: 0).isActive = true
        
        doingViewController.view.translatesAutoresizingMaskIntoConstraints = false
        doingViewController.view.heightAnchor.constraint(equalTo: doingContainerView.heightAnchor, constant: 0).isActive = true
        doingViewController.view.widthAnchor.constraint(equalTo: doingContainerView.widthAnchor, constant: 0).isActive = true
        doingViewController.view.centerXAnchor.constraint(equalTo: doingContainerView.centerXAnchor, constant: 0).isActive = true
        
        doneViewController.view.translatesAutoresizingMaskIntoConstraints = false
        doneViewController.view.heightAnchor.constraint(equalTo: doneContainerView.heightAnchor, constant: 0).isActive = true
        doneViewController.view.widthAnchor.constraint(equalTo: doneContainerView.widthAnchor, constant: 0).isActive = true
        doneViewController.view.centerXAnchor.constraint(equalTo: doneContainerView.centerXAnchor, constant: 0).isActive = true
    }
}
