//
//  MainViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/04.
//

import UIKit

class MainViewController: UIViewController {

    @IBOutlet weak var tableStackView: UIStackView!
    
    @IBOutlet weak var doingTable: UITableView!
    @IBOutlet weak var todoTable: UITableView!
    @IBOutlet weak var doneTable: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = "TO-DO-LIST"
        self.doingTable.backgroundColor = .blue
        self.tableStackView.backgroundColor = .green
        self.doneTable.backgroundColor = .brown
        
        
        self.view.backgroundColor = .white
    }

}
