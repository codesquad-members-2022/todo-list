//
//  TableViewController.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/11.
//

import UIKit

final class TableViewController: UIViewController{
    enum CellIdentifier: String{
        case tableCell = "tableCell"
        
        func getRawValue() -> String{
            return self.rawValue
        }
    }
    
    private var sectionHeader = [TableHeader]()
    private var todoTable = [TodoTableView]()
    let cardBoard: Board = Board()
    
    let todo = ["해야할 일", "하고있는 일", "끝난 일"]
    private var listIndex = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .systemBackground
    }
    
    func setTableAttributes(cell: CollectionCell, index: Int){
        let header = configureSectionHeader(index: index)
        let table = configureTableView(index: index)
        configureLayout(cell: cell, header: header, tableView: table)
    }
    
    @objc func setCardData(){
        DispatchQueue.main.async {
            self.todoTable.forEach{
                $0.reloadData()
            }
            
            for i in 0..<self.todo.count{
                self.sectionHeader[i].numberLabel.text = String(self.cardBoard[i].count)
            }
        }
    }
}

private extension TableViewController{
    func configureSectionHeader(index: Int) -> TableHeader{
        let header = TableHeader()
        header.titleLabel.text = todo[index]
        header.numberLabel.text = String(cardBoard[index].count)
        
        sectionHeader.append(header)
        
        return header
    }
    
    func configureTableView(index: Int) -> TodoTableView{
        let tableView = TodoTableView()
        tableView.setTableViewId(number: index)
        tableView.estimatedRowHeight = 108
        tableView.rowHeight = UITableView.automaticDimension
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(TodoCell.classForCoder(), forCellReuseIdentifier: CellIdentifier.tableCell.getRawValue())
        
        todoTable.append(tableView)
        
        return tableView
    }
    
    func configureLayout(cell: CollectionCell, header: TableHeader, tableView: TodoTableView){
        cell.contentView.addSubview(header)
        header.translatesAutoresizingMaskIntoConstraints = false
        header.leadingAnchor.constraint(equalTo: cell.contentView.leadingAnchor).isActive = true
        header.trailingAnchor.constraint(equalTo: cell.contentView.trailingAnchor).isActive = true
        header.topAnchor.constraint(equalTo: cell.contentView.topAnchor, constant: 51).isActive = true
        header.heightAnchor.constraint(equalToConstant: 26).isActive = true
        
        cell.contentView.addSubview(tableView)
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.leadingAnchor.constraint(equalTo: cell.contentView.leadingAnchor).isActive = true
        tableView.trailingAnchor.constraint(equalTo: cell.contentView.trailingAnchor).isActive = true
        tableView.topAnchor.constraint(equalTo: header.bottomAnchor, constant: 16).isActive = true
        tableView.bottomAnchor.constraint(equalTo: cell.contentView.bottomAnchor).isActive = true
    }
}

extension TableViewController: UITableViewDataSource, UITableViewDelegate{
    // Footer 관련 메서드(셀 간격용)
    func numberOfSections(in tableView: UITableView) -> Int {
        guard let customTable = tableView as? TodoTableView else { return 0 }
        let cards = cardBoard[customTable.tableViewId ?? 0]
        
        return cards.count
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
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CellIdentifier.tableCell.getRawValue()) as? TodoCell, let customTable = tableView as? TodoTableView else { return UITableViewCell() }
        
        let cards = cardBoard[customTable.tableViewId ?? 0]
        let cardData = cards[listIndex]
        cell.setLabelText(title: cardData.title, contents: cardData.content)
        
        if listIndex == cards.count - 1{
            listIndex = 0
        } else{
            listIndex += 1
        }
        
        return cell
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }
}
