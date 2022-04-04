//
//  TableViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/04.
//

import UIKit

class TableViewController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .clear
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 0
    }
}
