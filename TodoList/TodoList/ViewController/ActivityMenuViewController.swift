//
//  MenuViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/11.
//

import UIKit

class ActivityMenuViewController: UIViewController {

    private var closeButton: UIButton!
    private var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setAttributes()
    }
    
    func setAttributes(){
        self.view.backgroundColor = .white
        configureCloseButton()
    }
    
    func configureCloseButton(){
        closeButton = UIButton()
        closeButton.setImage(UIImage(named: "close"), for: .normal)
        closeButton.tintColor = .black
        
        self.view.addSubview(closeButton)
        closeButton.translatesAutoresizingMaskIntoConstraints = false
        closeButton.heightAnchor.constraint(equalToConstant: 10.5).isActive = true
        closeButton.widthAnchor.constraint(equalToConstant: 10.5).isActive = true
        closeButton.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 80).isActive = true
        closeButton.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -48).isActive = true
     

    }
    

    
    
    

}
