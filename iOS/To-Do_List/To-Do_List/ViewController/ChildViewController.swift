//
//  ToDoViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//

typealias MovedCardInfo = (foucsedCard:Todo,from:Int)

import UIKit

class ChildViewController: UIViewController {

    private(set) var tableView: BoardTableView<CardDisplayable,CardCell>!
    
    private var header : BoardHeader!
    var boardType : BoardType?
    
    //Notification
    static let tapCofirmButton = Notification.Name("didTapConfirmButton")
    static let tapMoveToCompltedButton = Notification.Name("tapMoveToCompltedButton")
    static let newCard = "newCard"
    static let movedCardInfo = "movedCardInfo"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .secondarySystemBackground
        addObserver()
        setHeader()
    }

    func removeFromList(todo: Todo) {
        
        DispatchQueue.main.async { [weak self] in
            guard let self = self else { return }
            self.tableView.removeTodo(todo: todo)
            self.header.updateCount(self.tableView.list.count)
        }
    }
    
    
    func updateList(todo: Todo) {
        DispatchQueue.main.async { [weak self] in
            guard let self = self else { return }
            self.tableView.updateCell(with:todo)
        }
    }
    
    func insertToList(todo:Todo) {
        
        DispatchQueue.main.async { [weak self] in
            guard let self = self else { return }
            self.tableView.appendTodo(todo: todo)
            self.header.updateCount(self.tableView.list.count)
        }
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
    

    func setTableView(list:[CardDisplayable]) {
        self.tableView = BoardTableView(
            frame: .zero,
            style: .plain,
            list: list,
            cellConfigurator: { card, cell in
            cell.loadCardInfo(info: card)
        })
        self.tableView.boardTableDelegate = self
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
            name: MainViewController.didFetchBoard,
            object: nil)
    }
    
    @objc func reloadTableView(notification:Notification) {
        guard let data = notification.userInfo?[MainViewController.UserInfoBoardData] as? NetworkResult , let boardType = self.boardType else { return }
        guard let cards = boardType.extractList(from: data) else {return}
        DispatchQueue.main.async { [weak self] in
            guard let self = self else { return }
            self.setTableView(list: cards)
            self.header.updateCount(cards.count)
        }
    }
}


//MARK: -- AddButton delegation
extension ChildViewController : BoardHeaderDelegate {
    func didTapAddButton() {
        let editVC = EditCardViewController()
        editVC.delegate = self
        editVC.setEditCardView(editStyle: .add)
        editVC.modalPresentationStyle = .formSheet
        present(editVC, animated: true)
    }
   
}

//MARK: -- BoardTableView Delete delegation
extension ChildViewController : BoardTableViewDelegate {
  

    func didTapMoveToCompleted(foucsedCard: Todo, from: Int) {
        NotificationCenter.default.post(
            name: ChildViewController.tapMoveToCompltedButton,
            object: self,
            userInfo: [ChildViewController.movedCardInfo:MovedCardInfo(foucsedCard:foucsedCard,from:from)])
    }
    
    func didTapEdit(item: Any) {
        guard let cardInfo = item as? Todo else {return}
        let editVC = EditCardViewController()
        editVC.delegate = self
        editVC.setEditCardView(editStyle: .editContent(cardInfo: cardInfo))
        editVC.modalPresentationStyle = .formSheet
        present(editVC, animated: true)
    }
    
    func didTapDelete(item: Any) {
        NotificationCenter.default.post(
            name: MainViewController.didDeleteCard,
            object: self,
            userInfo: [MainViewController.deletedCard:item])
    }
}

extension ChildViewController:EditViewControllerDelegate {
    func didTapConfirmButton(editViewInfo: EditViewInputInfo) {
        
        let newCardInfo = NewCard(
            id: nil,
            writer: "iOS",
            title: editViewInfo.title,
            content: editViewInfo.content,
            cardType:self.boardType?.type ?? "",
            memberId: 1, maxPositionNumber: self.tableView.list.count
        )
        
        NotificationCenter.default.post(
            name: ChildViewController.tapCofirmButton,
            object: self,
            userInfo: [ChildViewController.newCard:newCardInfo]
        )
    }
}


