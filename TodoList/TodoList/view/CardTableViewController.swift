//
//  TodoViewController.swift
//  TodoList
//
//  Created by Bibi on 2022/04/06.
//

import UIKit

class CardTableViewController: UIViewController {

    static let identifier = "CardTableViewController"
    
    @IBOutlet weak var cardLabel: UILabel!
    @IBOutlet weak var cardCountLabel: UILabel!
    @IBOutlet weak var addCardButton: UIButton!
    @IBOutlet weak var cardTableView: UITableView!
    
    private let tableViewDelegate = CardTableDelegate()
    private let tableViewDataSource = CardTableDataSource()
    private var cards = [TableCardUsable]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        cardTableView.register(CardTableViewCell.self, forCellReuseIdentifier: CardTableViewCell.identifier)
        cardTableView.delegate = tableViewDelegate
        cardTableView.dataSource = tableViewDataSource
        
        tableViewDataSource.setCards(cards: cards)
        
        setUIProperties()
        setUIPropertiesConstraint()
    }
    func setCardTitleLabel(title: String){
        cardLabel.text = title
    }
    
    func appendCard(_ card: TableCardUsable) {
        self.cards.append(card)
        self.tableViewDataSource.appendCards(card: card)
    }
    
    func setCards(_ cards: [TableCardUsable]) {
        self.cards = cards
        self.tableViewDataSource.setCards(cards: cards)
    }
    
    @IBAction func addCardButtonTouched(_ sender: UIButton) {
        let newCardVC = NewCardViewController()
        newCardVC.modalTransitionStyle = .coverVertical
        newCardVC.modalPresentationStyle = .automatic
        // setViewConstraintFor(viewController: newCardVC)
        self.present(newCardVC, animated: true)
    }
    
    private func setCardCountLabel() {
        cardCountLabel.text = "\(cards.count)"
    }
    
    private func setUIProperties() {
        view.backgroundColor = .systemGray5
        cardTableView.backgroundColor = .systemGray5
        cardCountLabel.backgroundColor = .lightGray
        cardCountLabel.layer.cornerRadius = 13.5
        cardCountLabel.layer.masksToBounds = true
    }
    
    private func setUIPropertiesConstraint() {
        self.cardTableView.translatesAutoresizingMaskIntoConstraints = false
        self.cardLabel.translatesAutoresizingMaskIntoConstraints = false
        self.cardCountLabel.translatesAutoresizingMaskIntoConstraints = false
        self.addCardButton.translatesAutoresizingMaskIntoConstraints = false
        
        self.cardLabel.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 20).isActive = true
        self.cardLabel.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 10).isActive = true
        
        self.cardTableView.topAnchor.constraint(equalTo: self.cardLabel.bottomAnchor, constant: 10).isActive = true
        self.cardTableView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor).isActive = true
        self.cardTableView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 10).isActive = true
        self.cardTableView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -10).isActive = true
        
        self.cardCountLabel.leftAnchor.constraint(equalTo: self.cardLabel.rightAnchor, constant: 15).isActive = true
        self.cardCountLabel.widthAnchor.constraint(equalToConstant: 30).isActive = true
        self.cardCountLabel.centerYAnchor.constraint(equalTo: self.cardLabel.centerYAnchor, constant: 0).isActive = true
        
        self.addCardButton.rightAnchor.constraint(equalTo: self.view.rightAnchor, constant: -10).isActive = true
        self.addCardButton.bottomAnchor.constraint(equalTo: self.cardTableView.topAnchor, constant: -10).isActive = true
    }
    
//    private func setViewConstraintFor(viewController: NewCardViewController) {
//        guard let newCardView = viewController.view else {
//            return
//        }
//        newCardView.translatesAutoresizingMaskIntoConstraints = false
//        newCardView.widthAnchor.constraint(equalToConstant: 500).isActive = true
//        newCardView.heightAnchor.constraint(equalToConstant: 300).isActive = true
//        newCardView.centerXAnchor.constraint(equalTo: self.view.centerXAnchor).isActive = true
//        newCardView.centerYAnchor.constraint(equalTo: self.view.centerYAnchor).isActive = true
//    }
}
