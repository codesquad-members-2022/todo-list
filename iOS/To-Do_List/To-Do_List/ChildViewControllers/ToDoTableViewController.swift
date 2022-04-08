//
//  ToDoTableViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//


import UIKit
import Foundation

class ToDoTableViewController: UIViewController {

    @IBOutlet var tableView: CustomTableView!
    var todoList:[Todo]?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        setTableView()
        addObserver()
    }
    
    
    private func setTableView() {
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.tableHeaderView = TabelViewHeader(titleText: "해야할 일")
        self.tableView.register(CardCell.self, forCellReuseIdentifier: CardCell.identifier)
        self.tableView.backgroundColor = .secondarySystemBackground
//        self.tableView.rowHeight = UITableView.automaticDimension
    }
    
    private func addObserver() {
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(reloadTableView),
            name: .didFetchInfo,
            object: nil)
    }
    
    @objc func reloadTableView(notification:Notification) {
        guard let data = notification.userInfo?[userInfo.taskData] as? Todoitems else { return }
        todoList = data.response.todoItems
        
        DispatchQueue.main.async {
            self.tableView.reloadData()
        }
    }
  

}

// MARK: - Table view data source
extension ToDoTableViewController : UITableViewDelegate , UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        guard let todoList = todoList else { return 0 }
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CardCell.identifier) as? CardCell else { return UITableViewCell() }
//        guard let todoList = self.todoList else { return UITableViewCell() }
//        cell.loadCardInfo(info: todoList[indexPath.row])
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        
        return UITableView.automaticDimension

    }
    
}
