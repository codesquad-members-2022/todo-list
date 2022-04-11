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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initChildViewControllers()
        setUIProperties()
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
        
        boardStackView.addArrangedSubview(todoViewController.view)
        boardStackView.addArrangedSubview(doingViewController.view)
        boardStackView.addArrangedSubview(doneViewController.view)
        
        addChild(todoViewController)
        addChild(doingViewController)
        addChild(doneViewController)
        
        todoViewController.didMove(toParent: self)
        doingViewController.didMove(toParent: self)
        doneViewController.didMove(toParent: self)
    }
    
    private func setUIProperties() {
        view.backgroundColor = .systemGray5
        boardStackView.backgroundColor = .systemGray5
        todoViewController?.setCardTitleLabel(title: CardStatus.todo.name)
        doingViewController?.setCardTitleLabel(title: CardStatus.doing.name)
        doneViewController?.setCardTitleLabel(title: CardStatus.done.name)
        todoViewController?.appendCard(factory.createRandomCard())
    }
}
