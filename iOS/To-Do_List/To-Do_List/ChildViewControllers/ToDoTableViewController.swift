//
//  ToDoTableViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//

typealias Todolist = [Todo]

import UIKit
import Foundation

class ToDoTableViewController: UITableViewController {
    
    var todoList:Todolist?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        tableView.separatorStyle = .none
        self.tableView.tableHeaderView = TabelViewHeader(titleText: "해야할 일")
        
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(reloadTableView),
            name: NSNotification.Name("DidFetchToDoList"),
            object: nil)
    }
    
    @objc func reloadTableView(notification:Notification) {
        guard let data = notification.userInfo?["TodoList"] as? Todoitems else { return }
        todoList =  data.response.todoItems
        
        
        DispatchQueue.main.async {
            self.tableView.reloadData()
        }
    }
    
    // MARK: - Table view data source

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "CardCell") as? CardCell ,
              let todoList = todoList else { return UITableViewCell() }
        
        let title = todoList[indexPath.row].title
        let body = todoList[indexPath.row].content

        cell.setCardText(title: title, body: body)
        return cell
    }
    
    
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard let todoList = todoList else { return 0 }
        return todoList.count
    }

}
