//
//  ToDoViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//


import UIKit


class ChildViewController: UIViewController, UITableViewDelegate {

    
    private var tableView: BoardTableView<Todo,CardCell>!
    @IBOutlet weak private var headerContainer: UIView!
    private var header : BoardHeader!
    private var boardType : BoardType?
    
    
    private var list:[Todo]?

    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .secondarySystemBackground
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
            self.setTableView()
            self.header.updateCount(self.list?.count)
            self.tableView.reloadData()
        }
        
    }
    
    private func setTableView() {
        guard let list = list else {return}
        self.tableView = BoardTableView(frame: CGRect(x: self.headerContainer.frame.minX, y: self.headerContainer.frame.maxY, width: view.frame.width, height: view.frame.height), style: .plain, list:list , cellConfigurator: { card, cell in
            cell.loadCardInfo(info: card)
        })
        self.view.addSubview(self.tableView)
        tableViewConstraints()
    }
}


extension ChildViewController {
    
    private func tableViewConstraints() {
        NSLayoutConstraint.activate([
            self.tableView.topAnchor.constraint(equalTo: self.headerContainer.bottomAnchor),
            self.tableView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),
            self.tableView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            self.tableView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor)
        ])
    }
}
