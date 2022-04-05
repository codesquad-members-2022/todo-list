//
//  ContentViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/05.
//

import UIKit

class ContentViewController: UIViewController {
    let todoList = [["Github공부하기","add,push,commit"],
                    ["Github공부하기","add,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commit,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commit,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commit"],
                    ["Github공부하기","add,push,commit"]]
    private var todoListTable: TodoTableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setTodoTableView()
        setTableViewDelegate()
        setCellHeight()
        todoListTable.register(TodoCell.classForCoder(), forCellReuseIdentifier: "todo")
    }
    
    func setTodoTableView(){
        todoListTable = TodoTableView(frame: CGRect(x: 0, y: 0, width: view.frame.width, height: (view.frame.height)*(5/6)))
        
        self.view = todoListTable
    }
    
    func setTableViewDelegate(){
        todoListTable.delegate = self
        todoListTable.dataSource = self
    }
}




extension ContentViewController: UITableViewDelegate, UITableViewDataSource {
    private func setCellHeight(){
        todoListTable.rowHeight = UITableView.automaticDimension
        todoListTable.estimatedRowHeight = 200
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 3
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "todo") as? TodoCell else { return UITableViewCell() }
        
        let data = todoList[indexPath.row]
        cell.setLabelText(title: data[0], contents: data[1])
        
        return cell
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }

}
