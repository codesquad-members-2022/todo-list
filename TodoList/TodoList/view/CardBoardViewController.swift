//
//  ViewController.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/05.
//

import UIKit

class CardBoardViewController: UIViewController {

    static let cardTableStoryBoardIdentifier = "CardTableStoryboard"
    
    let factory = CardFactory()
    let cardBoard = CardBoard()
    
    private var todoViewController: CardTableViewController?
    private var doingViewController: CardTableViewController?
    private var doneViewController: CardTableViewController?
    
    @IBOutlet weak var boardStackView: UIStackView!
    @IBOutlet weak var todoContainerView: UIView!
    @IBOutlet weak var doneContainerView: UIView!
    @IBOutlet weak var doingContainerView: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initChildViewControllers()
        setUIProperties()
        setUIPropertiesAutoLayout()
    }
    
    private func initChildViewControllers() {
        let todoStoryBoard = UIStoryboard(name: CardBoardViewController.cardTableStoryBoardIdentifier, bundle: Bundle(for: CardTableViewController.self))
        self.todoViewController = todoStoryBoard.instantiateViewController(withIdentifier: CardTableViewController.identifier) as? CardTableViewController
        self.doingViewController = todoStoryBoard.instantiateViewController(withIdentifier: CardTableViewController.identifier) as? CardTableViewController
        self.doneViewController = todoStoryBoard.instantiateViewController(withIdentifier: CardTableViewController.identifier) as? CardTableViewController
        
        guard let todoViewController = self.todoViewController,
              let doingViewController = self.doingViewController,
              let doneViewController = self.doneViewController else {
            return
        }
        
        addChild(todoViewController)
        addChild(doingViewController)
        addChild(doneViewController)
        
        todoContainerView.addSubview(todoViewController.view)
        doingContainerView.addSubview(doingViewController.view)
        doneContainerView.addSubview(doneViewController.view)
    }
    
    private func setUIProperties() {
        view.backgroundColor = .systemGray5
        boardStackView.backgroundColor = .systemGray5
        todoViewController?.setCardTitleLabel(title: CardStatus.todo.name)
        doingViewController?.setCardTitleLabel(title: CardStatus.doing.name)
        doneViewController?.setCardTitleLabel(title: CardStatus.done.name)
        todoViewController?.appendCard(factory.createRandomCard())
    }
    
    private func setUIPropertiesAutoLayout() {
        
        guard let todoViewController = self.todoViewController,
              let doingViewController = self.doingViewController,
              let doneViewController = self.doneViewController else {
            return
        }
        
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
