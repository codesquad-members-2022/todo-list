//
//  ToDoViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//


import UIKit

protocol BoardModifiable {
    func removeFromList(card: Todo)
    func setBoardType(type: BoardType)
}


class ChildViewController: UIViewController , BoardModifiable{
    

    private var tableView: BoardTableView<Todo,CardCell>!
    private var header : BoardHeader!
    private var boardType : BoardType?
    
    private var list:[Todo]?
    
   
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .secondarySystemBackground
        addObserver()
//        setTableView()
        setHeader()
    }

    func removeFromList(card: Todo) {
        guard var list = list, let targetIndex = list.firstIndex(where: {$0.id == card.id}) else {return}
        list.remove(at: targetIndex)
    }
    
    func setBoardType(type : BoardType) {
        self.boardType = type
    }
        
    private func setHeader() {
        guard let title = boardType else {return}
        self.header = BoardHeader(titleText: title)
        self.header.contentMode = .center
        self.header.delegate = self
        self.view.addSubview(header)
        setHeaderViewConstraint()
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
        self.tableView.BoardTableDelegate = self
        self.view.addSubview(tableView)
        setTableViewConstraint()
    }
}

//MARK: -- Constraint
extension ChildViewController {
    
    private func setHeaderViewConstraint() {
        header.translatesAutoresizingMaskIntoConstraints = false
            NSLayoutConstraint.activate([
                self.header.topAnchor.constraint(equalTo: self.view.topAnchor),
                self.header.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
                self.header.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
                self.header.heightAnchor.constraint(equalToConstant: 44)
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
            name: MainViewController.didFetchBoardInfo,
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


//MARK: -- AddButton delegation
extension ChildViewController : BoardHeaderDelegate {
    func DidTapAddButton() {
        let editVC = EditCardViewController()
        editVC.modalPresentationStyle = .formSheet
        present(editVC, animated: true)
    }
}

//MARK: -- BoardTableView Delete delegation
extension ChildViewController : BoardTableViewDelegate {
    func DidTapDelete(item: Any) {
        NotificationCenter.default.post(
            name: MainViewController.didDeleteCard,
            object: self,
            userInfo: [MainViewController.CardData:item])
    }
}



