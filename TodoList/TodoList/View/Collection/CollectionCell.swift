//
//  collectionCell.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/05.
//

import UIKit

class CollectionCell: UICollectionViewCell{
    private var todoTable: TodoTableView!
    var headerTitle: String?
    let cellIdentifier = "tableCell"
    let todoList = [["Github공부하기","add,push,commit"],
                    ["Github공부하기","add,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commit,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commit,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commit"],
                    ["Github공부하기","add,push,commit"]]
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setTodoTableView()
        addDelegate()
        setCellHeight()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setTodoTableView()
        addDelegate()
        setCellHeight()
    }
    
    func setTodoTableView(){
        todoTable = TodoTableView(frame: CGRect(x: 0, y: 0, width: contentView.frame.width, height: contentView.frame.height))
        todoTable.register(TableHeader.self, forHeaderFooterViewReuseIdentifier: "tableHeader")
        
        self.contentView.addSubview(todoTable)
    }
    
    func addDelegate(){
        todoTable.delegate = self
        todoTable.dataSource = self
        
        todoTable.register(TodoCell.classForCoder(), forCellReuseIdentifier: cellIdentifier)
    }
}

extension CollectionCell: UITableViewDataSource, UITableViewDelegate{
    private func setCellHeight(){
        todoTable.rowHeight = UITableView.automaticDimension
        todoTable.estimatedRowHeight = 200
    }
    
    // Header 관련 메서드
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 26
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        guard let header = todoTable.dequeueReusableHeaderFooterView(withIdentifier: "tableHeader") as? TableHeader else{ return nil}
        
        header.titleLabel.text = headerTitle
        header.numberLabel.text = "0"
        
        return header
    }
    
    // cell 관련 메서드
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 3
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier) as? TodoCell else { return UITableViewCell() }
        
        let data = todoList[indexPath.row]
        cell.setLabelText(title: data[0], contents: data[1])
        cell.backgroundColor = .systemGray5
        
        return cell
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }
}
