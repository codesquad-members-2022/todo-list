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
    private var networkManager = NetworkManager(session: URLSession(configuration: URLSessionConfiguration.default))
    
    //Notification
    static let didFetchInfo = NSNotification.Name("DidFetchToList")
    static let BoardData = "BoardData"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .secondarySystemBackground
        configureChildViewControllers()
        configureLogView()
        propagateData()
    }
    
    @IBAction func TapLogViewButton(_ sender: UIButton) {
        self.logViewContainer.isHidden = !logViewContainer.isHidden
    }

    
    private func propagateData() {
        networkManager.request(endpoint: EndPointCase.getBoard.endpoint) { (result:Result<NetworkResult,NetworkError>)  in
            switch result {
            case .success(let data):
                self.postNotification(data: data)
            case .failure(let error):
                os_log(.error, "\(error.localizedDescription)")
            }
        }
    }
    
    private func postNotification(data: NetworkResult) {
        NotificationCenter.default.post(
            name: MainViewController.didFetchInfo,
            object: self,
            userInfo: [MainViewController.BoardData:data])

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
    
    private func configureLogView(){
        self.logViewContainer.isHidden = true
    }
}


