//
//  ToDoViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//


import UIKit

final class ChildViewController: UIViewController, UITableViewDelegate {
    

    private var tableView: BoardTableView<Todo,CardCell>!
    private var header : BoardHeader!
    private var boardType : BoardType?
    private var editCardViewController:EditCardViewController?
    
    private var list:[Todo]?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .secondarySystemBackground
        addObserver()
        setTableView()
        setHeader()
    }

    func setBoardType(type : BoardType) {
        self.boardType = type
    }
        
    private func setHeader() {
        guard let title = boardType else {return}
        self.header = BoardHeader(titleText: title)
        self.view.addSubview(header)
        setHeaderViewConstraint()
        
        self.header.delegate = self
    }
    

    private func setTableView() {
        guard let list = list else {return}
        self.tableView = BoardTableView(
            frame: .zero,
            style: .plain,
            list: list,
            cellConfigurator: { card, cell in
            cell.loadCardInfo(info: card)
        })
        self.view.addSubview(tableView)
        setTableViewConstraint()
    }
}

//MARK: -- Constraint
extension ChildViewController {
    
    private func setHeaderViewConstraint() {
        let headerHeight:CGFloat = 44.0
        header.translatesAutoresizingMaskIntoConstraints = false
            NSLayoutConstraint.activate([
                self.header.topAnchor.constraint(equalTo: self.view.topAnchor),
                self.header.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
                self.header.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
                self.header.heightAnchor.constraint(equalToConstant: headerHeight)
                ])
    }
    
    private func setTableViewConstraint() {
        tableView.translatesAutoresizingMaskIntoConstraints = false
            NSLayoutConstraint.activate([
            self.tableView.topAnchor.constraint(equalTo: self.header.bottomAnchor),
            self.tableView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),
            self.tableView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            self.tableView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor)
        ])
    }
}



//MARK: -- NotificationCenter

extension ChildViewController {
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
}


//MARK: -- BoardHeader Delegation
extension ChildViewController : BoardHeaderDelegate {
    func didTapAddButton() {
        let editVC = EditCardViewController()
        editVC.setEditCardView(editStyle: .add)
        editVC.modalPresentationStyle = .formSheet
        
        editVC.delegate = self
        
        editCardViewController = editVC
        guard let editCardViewController = editCardViewController else { return }
        present(editCardViewController, animated: true)
    }
}


//MARK: -- EditViewController Delegation
extension ChildViewController : EditViewControllerDelegate {
    func didTapConfirmButton(editViewInfo: EditViewInputInfo) {
        postNotification(editViewInfo: editViewInfo)
    }
    
    func postNotification(editViewInfo:EditViewInputInfo) {
        
    }
}
