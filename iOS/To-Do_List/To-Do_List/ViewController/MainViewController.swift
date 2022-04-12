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
    static let didFetchBoardInfo = NSNotification.Name("DidFetchToList")
    static let didDeleteCard = NSNotification.Name("DidDeleteCard")
    
    //UserInfo
    static let BoardData = "BoardData"
    static let CardData = "CardData"
    

    //Constraint for logView animation
    private var logViewConstaints : [NSLayoutConstraint] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .secondarySystemBackground
        configureChildViewControllers()
        setNetworkManager()
        propagateData()
        addObserver()
        //removeCardFromChildController(card: Todo(id: 12, title: "ds", content: "dsd", createdAt: "sd"), from: ChildViewController)
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
            self.statckView.addArrangedSubview($0.view)
        }
        
    }

    
}

//MARK: Notification
extension MainViewController {
    
    private func postNotification(data: NetworkResult) {
        NotificationCenter.default.post(
            name: MainViewController.didFetchBoardInfo,
            object: self,
            userInfo: [MainViewController.BoardData:data])
    }
    
    private func addObserver() {
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(deleteCard),
            name: MainViewController.didDeleteCard,
            object: nil)
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
    
    @objc private func deleteCard(notification:Notification) {
        guard let cardInfo = notification.userInfo?[MainViewController.CardData] as? Todo,
              let childVC = notification.object as? ChildViewController else {return}
        
        networkManager?.request(endpoint: EndPointCase.deleteCard(card: cardInfo).endpoint) { (result:Result<String,NetworkError>)  in
            switch result {
            case .success:
                os_log(.default, "삭제 성공")
                self.removeCardFromChildController(card: cardInfo, from: childVC)
            case .failure(let error):
                os_log(.error, "\(error.localizedDescription)")
            }
        }
    }
    
    
    
    private func removeCardFromChildController(card: Todo, from: ChildViewController) {
        //여기서 childController 를 불러야한다.
        let BoardViewControllers = self.children.filter {$0 is BoardModifiable}
        
        //어떤 childController 인지 어떻게 앎?
        
        print(BoardViewControllers)
        
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
//            let bottom = self.logViewContainer.bottomAnchor.constraint(equalTo: self.view.bottomAnchor)
            
            self.logViewConstaints = [leading,trailing]
            NSLayoutConstraint.activate(self.logViewConstaints)
            
            self.logViewContainer.frame = CGRect(x: self.view.frame.maxX, y: self.view.safeAreaInsets.top, width: 0, height: self.logViewContainer.frame.height)
        }
    }
}
