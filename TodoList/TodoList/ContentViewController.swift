//
//  ContentViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/05.
//

import UIKit

class ContentViewController: UIViewController {

    
    let todoList = [["Github공부하기","add,push,commit"],
                    ["Github공부하기","add,push,commit"],
                    ["Github공부하기","add,push,commit"]]
    
    var todoListTable: TodoTableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setTodoTableView()

    }
    
    func setTodoTableView(){
        todoListTable = TodoTableView(frame: CGRect(x: 0, y: 0, width: view.frame.width, height: (view.frame.height)*(5/6)))
        
        self.view = todoListTable
    }
}




//extension ContentViewController: UITableViewDelegate, UITableViewDataSource {
//    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        <#code#>
//    }
//
//    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        <#code#>
//    }
//
//
//
//}
