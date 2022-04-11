//
//  ToDoViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//


import UIKit


class ChildViewController: UIViewController, UITableViewDelegate {

    
    @IBOutlet weak private var tableView: BoardTableView!
    @IBOutlet weak private var headerContainer: UIView!
    private var header : BoardHeader!
    private var boardType : BoardType?
    
    
    //Custom DataSource
    private var dataSource : BoardTableViewDataSource!
    
    private var list:[Todo]?

    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .secondarySystemBackground
        setTableView()
        setHeader()
        addObserver()
        
    }

    func setBoardType(type : BoardType) {
        self.boardType = type
    }
    
    private func setHeader() {
        guard let title = boardType else {return}
        self.header = BoardHeader(titleText: title)
        headerContainer.addSubview(header)
    }
    
    private func setTableView() {
        self.tableView.delegate = self
    }
    
    private func setTableViewDataSource() {
       guard let list = list else {return}
        self.dataSource = BoardTableViewDataSource(list: list, reuseIdentifier: CardCell.identifier, cellConfigurator: { card, cell in
           guard let cardCell = cell as? CardCell else{return}
           cardCell.loadCardInfo(info: card)
        })
        self.tableView.dataSource = self.dataSource
    }
        
    private func addObserver() {
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(reloadTableView),
            name: MainViewController.didFetchInfo,
            object: nil)
    }
    
    @objc func reloadTableView(notification:Notification) {
        guard let data = notification.userInfo?[MainViewController.BoardData] as? NetworkResult , let boardType = self.boardType else { return }
        list = boardType.extractList(from: data)
        
        DispatchQueue.main.async {
            self.setTableViewDataSource()
            self.header.updateCount(self.list?.count)
            self.tableView.reloadData()
        }
        
    }
}


