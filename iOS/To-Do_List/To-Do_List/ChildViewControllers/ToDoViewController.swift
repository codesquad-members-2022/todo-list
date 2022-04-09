//
//  ToDoViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//


import UIKit
import Foundation

class ToDoViewController: UIViewController {

    
    @IBOutlet weak var tableView: BoardTableView!
    
    @IBOutlet weak var headerContainer: UIView!
    
    var todoList:[Todo]?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .secondarySystemBackground
        setTableView()
//        addObserver()
        var header = BoardHeader(titleText: "해야할 일")

        headerContainer.addSubview(header)
        
    }
    
    private func setTableView() {

        self.tableView.delegate = self
        self.tableView.dataSource = self
    }
    
    private func addObserver() {
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(reloadTableView),
            name: .didFetchInfo,
            object: nil)
    }
    
    @objc func reloadTableView(notification:Notification) {
        guard let data = notification.userInfo?[userInfo.BoardData] as? NetworkResult else { return }
        todoList = data.response.todoItems
        
        DispatchQueue.main.async {
            self.tableView.reloadData()
        }
    }
  

}

// MARK: - Table view data source
extension ToDoViewController : UITableViewDelegate , UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        guard let todoList = todoList else { return 0 }
        return 10
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CardCell.identifier) as? CardCell else { return UITableViewCell() }
//        guard let todoList = self.todoList else { return UITableViewCell() }
//        cell.loadCardInfo(info: todoList[indexPath.row])
        return cell
    }
    
 
}


