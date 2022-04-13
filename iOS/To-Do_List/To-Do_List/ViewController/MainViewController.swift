//
//  MainViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/04.
//

import UIKit
import OSLog

struct postModel:Codable {
    let writer:String?
    let position:Int?
    let title:String
    let content:String
    let cardType:String
    let memberId:Int?
    
    func toBody() -> [String:Any] {
        ["writer":writer, "position":position, "title": title,"content":content,"cardType":cardType,"memberId":memberId]
    }
    
}

final class MainViewController: UIViewController {
    
    //View
    @IBOutlet weak private var statckView: UIStackView!
    @IBOutlet weak private var logViewContainer: UIView!
    
    //Network
    private var networkManager:NetworkManager?
    
    //Notification
    static let didFetchInfo = NSNotification.Name("DidFetchToList")
    static let BoardData = "BoardData"
    
    //Constraint
    private var logViewConstaints : [NSLayoutConstraint] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .secondarySystemBackground
        configureChildViewControllers()
        propagateData()
    }
    
    private func propagateData() {
        networkManager = NetworkManager(session: URLSession(configuration: URLSessionConfiguration.default))
        
        networkManager?.request(endpoint: EndPointCase.getBoard.endpoint) { (result:Result<NetworkResult,NetworkError>)  in
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
