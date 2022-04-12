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
    private var historyViewController: HistoryViewController?
    
    //private let historyView = HistoryView(frame: .zero)
    private var historyViewBeforeConstraint: NSLayoutConstraint?
    private var historyViewAfterConstraint: NSLayoutConstraint?
    
    @IBOutlet weak var boardStackView: UIStackView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initChildViewControllers()
        setUIProperties()
        setHistoryView()
        cardBoard.cardBoardDelegate = self
    }
    
    private func setHistoryView(){
        guard let historyView = historyViewController?.view else { return }
        view.addSubview(historyView)
        //historyView.actionDelegate = self
        historyView.translatesAutoresizingMaskIntoConstraints = false
        historyView.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        historyView.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        historyView.widthAnchor.constraint(equalToConstant: 300).isActive = true
        historyViewBeforeConstraint = historyView.leadingAnchor.constraint(equalTo: view.trailingAnchor)
        historyViewAfterConstraint = historyView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor)
        historyViewBeforeConstraint?.isActive = true
    }
    
    private func initChildViewControllers() {
        let todoStoryBoard = UIStoryboard(name: CardBoardViewController.cardTableStoryBoardIdentifier, bundle: Bundle(for: CardTableViewController.self))
        self.todoViewController = todoStoryBoard.instantiateViewController(withIdentifier: CardTableViewController.identifier) as? CardTableViewController
        self.doingViewController = todoStoryBoard.instantiateViewController(withIdentifier: CardTableViewController.identifier) as? CardTableViewController
        self.doneViewController = todoStoryBoard.instantiateViewController(withIdentifier: CardTableViewController.identifier) as? CardTableViewController
        self.historyViewController = HistoryViewController()
        
        guard let todoViewController = self.todoViewController,
              let doingViewController = self.doingViewController,
              let doneViewController = self.doneViewController,
              let historyViewController = self.historyViewController else {
                  return
              }
        
        boardStackView.addArrangedSubview(todoViewController.view)
        boardStackView.addArrangedSubview(doingViewController.view)
        boardStackView.addArrangedSubview(doneViewController.view)
        
        addChild(todoViewController)
        addChild(doingViewController)
        addChild(doneViewController)
        addChild(historyViewController)
        
        todoViewController.didMove(toParent: self)
        doingViewController.didMove(toParent: self)
        doneViewController.didMove(toParent: self)
        historyViewController.didMove(toParent: self)
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
        cardBoard.historyButtonTapped()
    }
    
    private func historyAppearAnimate(){
        UIView.animate(withDuration: 0.3) {
            self.historyViewBeforeConstraint?.isActive = false
            self.historyViewAfterConstraint?.isActive = true
            self.view.layoutIfNeeded()
        }
    }
    
    private func historyDisappearAnimate(){
        UIView.animate(withDuration: 0.3) {
            self.historyViewAfterConstraint?.isActive = false
            self.historyViewBeforeConstraint?.isActive = true
            self.view.layoutIfNeeded()
        }
    }
}

extension CardBoardViewController: HistoryViewAction{
    func closeButtonTapped() {
        cardBoard.historyButtonTapped()
    }
}

extension CardBoardViewController: CardBoardAction{
    func historyViewHiddenChanged(_ hiddenState: HiddenState) {
        if hiddenState == .hidden{
            historyDisappearAnimate()
        }
        if hiddenState == .show{
            historyAppearAnimate()
        }
    }
}
