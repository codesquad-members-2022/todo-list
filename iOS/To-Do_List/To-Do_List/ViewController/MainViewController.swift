//
//  MainViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/04.
//

import UIKit
import OSLog

class MainViewController: UIViewController {
    
    //View
    @IBOutlet weak private var statckView: UIStackView!
    @IBOutlet weak private var logViewContainer: UIView!
    
    //Network
    private var networkManager:NetworkManager?
    
    //Notification
    static let didFetchBoard = NSNotification.Name("DidFetchToList")
    static let didDeleteCard = NSNotification.Name("DidDeleteCard")
    static let UserInfoBoardData = "BoardData"
    static let deletedCard = "CardData"
    

    //Constraint for logView animation
    private var logViewConstaints : [NSLayoutConstraint] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .secondarySystemBackground
        configureChildViewControllers()
        setNetworkManager()
        propagateData()
        addObserver()
    }
    
    private func configureChildViewControllers() {
        let storyBoard = UIStoryboard(name: "Main", bundle: .main)
        
        guard let todoViewController = storyBoard.instantiateViewController(withIdentifier: "ChildViewController") as? ChildViewController,
              let progressingTableViewController = storyBoard.instantiateViewController(withIdentifier: "ChildViewController") as? ChildViewController,
              let completedTableViewController = storyBoard.instantiateViewController(withIdentifier: "ChildViewController") as? ChildViewController else { return }
        
        todoViewController.setBoardType(type: .todo)
        progressingTableViewController.setBoardType(type: .progressing)
        completedTableViewController.setBoardType(type: .completed)
        
        [todoViewController,progressingTableViewController,completedTableViewController].forEach {
            addChild($0)
            
            $0.setTableView(list: [])
            self.statckView.addArrangedSubview($0.view)
        }
    }
}

//MARK: Notification
extension MainViewController {
    
    private func postNotification(data: NetworkResult) {
        NotificationCenter.default.post(
            name: MainViewController.didFetchBoard,
            object: self,
            userInfo: [MainViewController.UserInfoBoardData:data])
    }
    
    private func addObserver() {
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(deleteCard),
            name: MainViewController.didDeleteCard,
            object: nil)
        
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(postNewCardInfo),
            name: ChildViewController.tapCofirmButton,
            object: nil)
        
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(fetchMovedToCompletedCard),
            name: ChildViewController.tapMoveToCompltedButton,
            object: nil
        )
        
    }
}

//MARK: Network
extension MainViewController {
    
    private func setNetworkManager() {
        self.networkManager = NetworkManager(session: URLSession(configuration: URLSessionConfiguration.default))
    }
    
    private func propagateData() {
        networkManager?.request(endpoint: EndPointCase.getBoard.endpoint) { (result:Result<NetworkResult,NetworkError>)  in
            switch result {
            case .success(let data):
                self.postNotification(data: data)
            case .failure(let error):
                os_log(.error, "\(error.localizedDescription)")
            }
        }
    }
    
    @objc func fetchMovedToCompletedCard(_ notification:Notification) {
        guard let info = notification.userInfo?[ChildViewController.movedCardInfo] as? MovedCardInfo,
              let childVC = notification.object as? ChildViewController,
              let completedViewController = self.children.last as? ChildViewController
        else { return }
        
        let cardInfo = info.foucsedCard
        let originPosition = info.from
        let maxPositionNumber = completedViewController.tableView.list.count - 1
        
        let movedCard = MovedCard(
            id: cardInfo.id ?? 0,
            originPosition: originPosition,
            maxPositionNumber: maxPositionNumber,
            originCardType: cardInfo.cardType ?? "",
            goalCardType: completedViewController.boardType?.type ?? ""
        )

        networkManager?.request(endpoint: EndPointCase.moveToCompleted(movedCard: movedCard).endpoint, completionHandler: { (result:Result<NewTodo,NetworkError>) in
            switch result {
            case .success(let newTodo):
                childVC.removeFromList(todo: newTodo.response)
                completedViewController.insertToList(todo: newTodo.response)
            case .failure(let error):
                os_log(.error, "\(error.localizedDescription)")
            }
        }
    )
}
    
    @objc func postNewCardInfo(_ notification:Notification) {
        
        guard let newCard = notification.userInfo?[ChildViewController.newCard] as? NewCard,
              let childVC = notification.object as? ChildViewController
        else { return }
        
        //Add new card if card no have id
        if newCard.id == nil {
                networkManager?.request(endpoint: EndPointCase.addCard(card: newCard).endpoint, completionHandler: { (result:Result<NewTodo,NetworkError>) in
                    switch result {
                    case .success(let newTodo):
                        childVC.insertToList(todo: newTodo.response)
                    case .failure(let error):
                        os_log(.error, "\(error.localizedDescription)")
                    }
                }
            )
        } else {
                networkManager?.request(endpoint: EndPointCase.editCard(card: newCard).endpoint, completionHandler: { (result:Result<NewTodo,NetworkError>) in
                    switch result {
                    case .success(let newTodo):
                        childVC.updateList(todo: newTodo.response)
                    case .failure(let error):
                        os_log(.error, "\(error.localizedDescription)")
                    }
                }
            )
        }
    }
    
    @objc private func deleteCard(notification:Notification) {
        guard let cardInfo = notification.userInfo?[MainViewController.deletedCard] as? Todo,
              let id = cardInfo.id,
              let childVC = notification.object as? ChildViewController
        else { return }
        
        networkManager?.request(endpoint: EndPointCase.deleteCard(cardId: id).endpoint) { (result:Result<String,NetworkError>)  in
            switch result {
            case .success:
                childVC.removeFromList(todo: cardInfo)
            case .failure(let error):
                os_log(.error, "\(error.localizedDescription)")
            }
        }
    }
}


//MARK: Handeling Logview animation.
extension MainViewController {
    @IBAction func TapShowLogViewButton(_ sender: UIButton) {
        UIView.animate(withDuration: 1) {
            NSLayoutConstraint.deactivate(self.logViewConstaints)
            self.logViewConstaints.removeAll()
            
            let leading = self.logViewContainer.leadingAnchor.constraint(equalTo: self.statckView.trailingAnchor, constant: -100)
            self.logViewConstaints = [leading]
            NSLayoutConstraint.activate(self.logViewConstaints)
            self.logViewContainer.frame = CGRect(x: 0, y: 0, width: self.logViewContainer.frame.width, height: self.logViewContainer.frame.height)
        }
    }
    
   func TapCloseLogViewButton() {
        UIView.animate(withDuration: 1) {
            NSLayoutConstraint.deactivate(self.logViewConstaints)
            self.logViewConstaints.removeAll()

            let leading = self.logViewContainer.leadingAnchor.constraint(equalTo: self.view.trailingAnchor)
            let trailing = self.logViewContainer.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: self.logViewContainer.frame.width)

            
            self.logViewConstaints = [leading,trailing]
            NSLayoutConstraint.activate(self.logViewConstaints)
            
            self.logViewContainer.frame = CGRect(x: self.view.frame.maxX, y: self.view.safeAreaInsets.top, width: 0, height: self.logViewContainer.frame.height)
        }
    }
}
