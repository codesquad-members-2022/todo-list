//
//  DoneTableViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//

import UIKit

class DoneTableViewController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        self.view.backgroundColor = .brown
        self.tableView.tableHeaderView = TabelViewHeader(titleText: "하고있는일")
        self.tableView.tableHeaderView?.backgroundColor = .gray
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        
        return 0
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return 0
    }

}
