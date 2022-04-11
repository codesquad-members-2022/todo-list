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
    
    private lazy var historyView = HistoryView()
    private var historyViewBeforeConstraints: [NSLayoutConstraint] = []
    private var historyViewAfterConstraints: [NSLayoutConstraint] = []
    
    @IBOutlet weak var boardStackView: UIStackView!
    @IBOutlet weak var historyAppearButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initChildViewControllers()
        setUIProperties()
        setHistoryView()
    }
    
    private func setHistoryView(){
        self.view.addSubview(historyView)
        
        historyView.translatesAutoresizingMaskIntoConstraints = false
        
        historyViewBeforeConstraints.append(historyView.topAnchor.constraint(equalTo: view.topAnchor))
        historyViewBeforeConstraints.append(historyView.bottomAnchor.constraint(equalTo: view.bottomAnchor))
        historyViewBeforeConstraints.append(historyView.leadingAnchor.constraint(equalTo: view.trailingAnchor))
        historyViewBeforeConstraints.append(historyView.widthAnchor.constraint(equalToConstant: CGFloat(300)))
       
        historyViewAfterConstraints.append(historyView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor))
        historyViewAfterConstraints.append(historyView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor))
        historyViewAfterConstraints.append(historyView.topAnchor.constraint(equalTo: self.view.topAnchor))
        historyViewAfterConstraints.append(historyView.widthAnchor.constraint(equalToConstant: CGFloat(300)))
        
        historyViewBeforeConstraints.map{$0.isActive = true}
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
    @IBAction func historyAppearButtonTapped(_ sender: Any) {
        UIView.animate(withDuration: 0.5) {
            self.historyViewBeforeConstraints.map{$0.isActive = false}
            self.historyViewAfterConstraints.map{$0.isActive = true}
            self.view.layoutIfNeeded()
        }
    }
}
