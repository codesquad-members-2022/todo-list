//
//  ContentViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/05.
//

import UIKit

class ContentViewController: UIViewController {
    private var collectionView: CollectionView!
    private var todoTable: [TodoTableView]!
    private var sectionHeader: [TableHeader]!
    
    let todo = ["해야할 일", "하고있는 일", "끝난 일"]
    let todoList = [
                    ["Github공부하기","add,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commit,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commit,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commitadd,push,commit"]]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setCollectionView()
        setCollectionViewDelegate()
        setTableAttributes()
    }
}

private extension ContentViewController{
    func setCollectionView(){
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        
        collectionView = CollectionView(frame: CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height), collectionViewLayout: layout)
        
        self.view.addSubview(collectionView)
        
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        collectionView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 48).isActive = true
        collectionView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor).isActive = true
        collectionView.topAnchor.constraint(equalTo: self.view.topAnchor).isActive = true
        collectionView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor).isActive = true
    }
    
    func setCollectionViewDelegate(){
        collectionView.delegate = self
        collectionView.dataSource = self
        collectionView.register(CollectionCell.classForCoder(), forCellWithReuseIdentifier: "collectionCell")
    }
    
    func setTableAttributes(){
        configureSectionHeader()
        configureTableView()
    }
    
    func configureSectionHeader(){
        for doing in todo{
            let header = TableHeader()
            header.titleLabel.text = doing
            header.numberLabel.text = "0"
            
            if sectionHeader == nil{
                sectionHeader = [header]
            } else{
                sectionHeader.append(header)
            }
        }
    }
    
    func configureTableView(){
        for _ in todo{
            let tableView = TodoTableView()
            tableView.register(TableHeader.self, forHeaderFooterViewReuseIdentifier: "tableHeader")
            tableView.estimatedRowHeight = 108
            tableView.rowHeight = UITableView.automaticDimension
            tableView.delegate = self
            tableView.dataSource = self
            tableView.register(TodoCell.classForCoder(), forCellReuseIdentifier: "cellIdentifier")
            
            if todoTable == nil{
                todoTable = [tableView]
            } else{
                todoTable.append(tableView)
            }
        }
    }
}

extension ContentViewController: UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout{
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 3
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "collectionCell", for: indexPath) as? CollectionCell else { return UICollectionViewCell() }
        
        let header = sectionHeader[indexPath.row]
        cell.contentView.addSubview(header)
        header.translatesAutoresizingMaskIntoConstraints = false
        header.leadingAnchor.constraint(equalTo: cell.contentView.leadingAnchor).isActive = true
        header.trailingAnchor.constraint(equalTo: cell.contentView.trailingAnchor).isActive = true
        header.topAnchor.constraint(equalTo: cell.contentView.topAnchor, constant: 51).isActive = true
        header.heightAnchor.constraint(equalToConstant: 26).isActive = true
        
        let table = todoTable[indexPath.row]
        cell.contentView.addSubview(table)
        table.translatesAutoresizingMaskIntoConstraints = false
        table.leadingAnchor.constraint(equalTo: cell.contentView.leadingAnchor).isActive = true
        table.trailingAnchor.constraint(equalTo: cell.contentView.trailingAnchor).isActive = true
        table.topAnchor.constraint(equalTo: header.bottomAnchor, constant: 16).isActive = true
        table.bottomAnchor.constraint(equalTo: cell.contentView.bottomAnchor).isActive = true
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width: CGFloat = 258
        let size = CGSize(width: width, height: self.view.frame.height)
        return size
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 16)
    }
}

extension ContentViewController: UITableViewDataSource, UITableViewDelegate{
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
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "cellIdentifier") as? TodoCell else { return UITableViewCell() }
        
        let data = todoList[indexPath.row]
        cell.setLabelText(title: data[0], contents: data[1])
        
        return cell
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }
}
