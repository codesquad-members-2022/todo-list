//
//  KanbanColumnViewController.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/07.
//

import UIKit

class KanbanColumnViewController: UIViewController {
    
    private let tableTitleView = TableTitleView()
    private let tableView = UITableView(frame: .zero, style: .grouped)
    private let tableViewDataSource = KanbanTableViewDataSource()
    
    private let type: KanbanType
    
    init(type: KanbanType) {
        self.type = type
        super.init(nibName: nil, bundle: nil)
    }
    
    @available(*, unavailable) required init?(coder: NSCoder) {
        fatalError("Init with coder is unavailable")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpView()
    }
    
    private func setUpView() {
        view.addSubview(tableTitleView)
        view.addSubview(tableView)
        
        configureCustomTableView()
        configureTableTitleView()
        
        layoutTableTitleView()
        layoutTableView()
    }
    
    private func configureCustomTableView() {
        tableView.register(KanbanTableViewCell.self, forCellReuseIdentifier: KanbanTableViewCell.indentifier)
        tableView.dataSource = tableViewDataSource
        tableView.separatorStyle = .none
        tableView.sectionHeaderHeight = 8
        tableView.sectionFooterHeight = 8
        tableView.backgroundColor = .clear
    }
    
    private func configureTableTitleView() {
        tableTitleView.changeBadgeLabel(text: KanbanTableCellData.dataList.count)
        tableTitleView.changeTitleLabel(text: type.title)
    }
    
    private func layoutTableTitleView() {
        tableTitleView.translatesAutoresizingMaskIntoConstraints = false
        
        tableTitleView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor).isActive = true
        tableTitleView.bottomAnchor.constraint(equalTo: tableView.topAnchor).isActive = true
        tableTitleView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor).isActive = true
        tableTitleView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor).isActive = true
        tableTitleView.heightAnchor.constraint(equalToConstant: 26).isActive = true
    }
    
    private func layoutTableView() {
        tableView.translatesAutoresizingMaskIntoConstraints = false
        
        tableView.topAnchor.constraint(equalTo: tableTitleView.bottomAnchor).isActive = true
        tableView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor).isActive = true
        tableView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor).isActive = true
        tableView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor).isActive = true
    }
}
