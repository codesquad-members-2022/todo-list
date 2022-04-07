//
//  collectionCell.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/05.
//

import UIKit

class CollectionCell: UICollectionViewCell{
    private var todoTable: TodoTableView!
    private var sectionHeader: TableHeader!
    let cellIdentifier = "tableCell"
    let todoList = [["Github공부하기","add,push,commit"],
                    ["Github공부하기","add,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commit,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commit,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commit"],
                    ["Github공부하기","add,push,commit"]]
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setAttributes()
        addDelegate()
        setCellHeight()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setAttributes()
        addDelegate()
        setCellHeight()
    }
    
    func changeHeaderText(text: String){
        sectionHeader.titleLabel.text = text
    }
}

private extension CollectionCell{
    func setAttributes(){
        configureSectionHeader()
        configureTableView()
    }
    
    func configureSectionHeader(){
        sectionHeader = TableHeader()
        sectionHeader.titleLabel.text = "해야할 일"
        sectionHeader.numberLabel.text = "0"
        self.contentView.addSubview(sectionHeader)
        
        sectionHeader.translatesAutoresizingMaskIntoConstraints = false
        sectionHeader.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor).isActive = true
        sectionHeader.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor).isActive = true
        sectionHeader.topAnchor.constraint(equalTo: self.contentView.topAnchor, constant: 51).isActive = true
        sectionHeader.heightAnchor.constraint(equalToConstant: 26).isActive = true
    }
    
    func configureTableView(){
        todoTable = TodoTableView()
        todoTable.register(TableHeader.self, forHeaderFooterViewReuseIdentifier: "tableHeader")
        
        self.contentView.addSubview(todoTable)
        
        todoTable.translatesAutoresizingMaskIntoConstraints = false
        todoTable.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor).isActive = true
        todoTable.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor).isActive = true
        todoTable.topAnchor.constraint(equalTo: sectionHeader.bottomAnchor, constant: 16).isActive = true
        todoTable.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor).isActive = true
    }
    
    func addDelegate(){
        todoTable.delegate = self
        todoTable.dataSource = self
        
        todoTable.register(TodoCell.classForCoder(), forCellReuseIdentifier: cellIdentifier)
    }
}

extension CollectionCell: UITableViewDataSource, UITableViewDelegate{
    private func setCellHeight(){
        todoTable.estimatedRowHeight = 108
        todoTable.rowHeight = UITableView.automaticDimension
    }
    
    // Footer 관련 메서드(셀 간격용)
    func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }

    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 16
    }
    
    func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
        return nil
    }
    
    // cell 관련 메서드
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier) as? TodoCell else { return UITableViewCell() }
        
        let data = todoList[indexPath.row]
        cell.setLabelText(title: data[0], contents: data[1])
        
        return cell
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }
}
