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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUIProperties()
        setUIPropertiesConstraint()
    }
    
    func setUIProperties() {
        cardTableView.backgroundColor = .gray
        todoCountLabel.backgroundColor = .lightGray
        
        todoCountLabel.layer.cornerRadius = 15
        todoCountLabel.layer.masksToBounds = true
    }
    
    func setUIPropertiesConstraint() {
        
        self.view.translatesAutoresizingMaskIntoConstraints = false
        self.cardTableView.translatesAutoresizingMaskIntoConstraints = false
        self.todoLabel.translatesAutoresizingMaskIntoConstraints = false
        self.todoCountLabel.translatesAutoresizingMaskIntoConstraints = false
        self.addCardButton.translatesAutoresizingMaskIntoConstraints = false
        
        self.view.widthAnchor.constraint(equalToConstant: CGFloat(266.5)).isActive = true
        self.view.heightAnchor.constraint(equalToConstant: CGFloat(675)).isActive = true
        
        self.cardTableView.widthAnchor.constraint(equalTo: self.view.widthAnchor).isActive = true
        self.cardTableView.heightAnchor.constraint(equalTo: self.view.heightAnchor).isActive = true
        
        self.todoLabel.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 0).isActive = true
        self.todoLabel.bottomAnchor.constraint(equalTo: self.cardTableView.topAnchor, constant: -10).isActive = true
        
        self.todoCountLabel.leftAnchor.constraint(equalTo: self.todoLabel.rightAnchor, constant: 15).isActive = true
        self.todoCountLabel.centerYAnchor.constraint(equalTo: self.todoLabel.centerYAnchor, constant: 0).isActive = true
        self.todoCountLabel.widthAnchor.constraint(equalToConstant: 30).isActive = true
        
        self.addCardButton.rightAnchor.constraint(equalTo: self.view.rightAnchor, constant: 0).isActive = true
        self.addCardButton.bottomAnchor.constraint(equalTo: self.cardTableView.topAnchor, constant: -10).isActive = true
    }
}
