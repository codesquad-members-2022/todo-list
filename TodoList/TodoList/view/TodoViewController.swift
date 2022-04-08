//
//  TodoViewController.swift
//  TodoList
//
//  Created by Bibi on 2022/04/06.
//

import UIKit

class TodoViewController: UIViewController {

    @IBOutlet weak var todoLabel: UILabel!
    @IBOutlet weak var todoCountLabel: UILabel!
    @IBOutlet weak var addCardButton: UIButton!
    @IBOutlet weak var cardTableView: UITableView!
    private let tableViewDelegate = CardBoardTableDelegate()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUIProperties()
        setUIPropertiesConstraint()
        cardTableView.register(CardTableViewCell.self, forCellReuseIdentifier: "CardTableViewCell")
        cardTableView.delegate = tableViewDelegate
        cardTableView.dataSource = tableViewDelegate
        tableViewDelegate.setCards(cards: [Card(status: .todo, title: "firstCard", contents: "firstContents", writer: "ebony")])
        view.backgroundColor = .systemGray5
    }
    
    func setTitleLabel(title: String){
        todoLabel.text = title
    }
    
    private func setUIProperties() {
        cardTableView.backgroundColor = .systemGray5
        todoCountLabel.backgroundColor = .lightGray
        todoCountLabel.layer.cornerRadius = 13.5
        todoCountLabel.layer.masksToBounds = true
    }
    
    private func setUIPropertiesConstraint() {
        self.cardTableView.translatesAutoresizingMaskIntoConstraints = false
        self.todoLabel.translatesAutoresizingMaskIntoConstraints = false
        self.todoCountLabel.translatesAutoresizingMaskIntoConstraints = false
        self.addCardButton.translatesAutoresizingMaskIntoConstraints = false
        
        self.todoLabel.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 20).isActive = true
        self.todoLabel.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 10).isActive = true
        
        self.cardTableView.topAnchor.constraint(equalTo: self.todoLabel.bottomAnchor, constant: 10).isActive = true
        self.cardTableView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor).isActive = true
        self.cardTableView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 10).isActive = true
        self.cardTableView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -10).isActive = true
        
        self.todoCountLabel.leftAnchor.constraint(equalTo: self.todoLabel.rightAnchor, constant: 15).isActive = true
        self.todoCountLabel.centerYAnchor.constraint(equalTo: self.todoLabel.centerYAnchor, constant: 0).isActive = true
        self.todoCountLabel.widthAnchor.constraint(equalToConstant: 30).isActive = true
        
        self.addCardButton.rightAnchor.constraint(equalTo: self.view.rightAnchor, constant: -10).isActive = true
        self.addCardButton.bottomAnchor.constraint(equalTo: self.cardTableView.topAnchor, constant: -10).isActive = true
    }
}
