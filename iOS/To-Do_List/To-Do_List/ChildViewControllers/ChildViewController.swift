//
//  ToDoViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//


import UIKit


class ChildViewController: UIViewController {

    
    @IBOutlet weak private var tableView: BoardTableView!
    @IBOutlet weak private var headerContainer: UIView!
    private var header : BoardHeader!
    private var boardType : BoardType?
    
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
        self.tableView.dataSource = self
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
            self.tableView.reloadData()
        }
    }
}

// MARK: - Table view data source
extension ChildViewController : UITableViewDelegate , UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        guard let list = list else { return 0 }
//        self.header.updateCount(list.count)
        return 10
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CardCell.identifier) as? CardCell else { return UITableViewCell() }
//        guard let list = self.list else { return UITableViewCell() }
//        cell.loadCardInfo(info: list[indexPath.row])
        return cell
    }
    
 
}


