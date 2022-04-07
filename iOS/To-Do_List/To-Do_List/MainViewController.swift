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
    @IBOutlet weak var statckView: UIStackView!
    @IBOutlet weak var logViewContainer: UIView!
    
    //Network
    private var networkManager = NetworkManager()
    
        override func viewDidLoad() {
            super.viewDidLoad()
            self.view.backgroundColor = .white
            addChildViewControllers()
            propagateData()
    }
        
    @IBAction func TapLogViewButton(_ sender: UIButton) {
        self.logViewContainer.isHidden = !logViewContainer.isHidden
    }
    
    
    private func propagateData() {
        networkManager.getRequest { (result:Result<Todoitems,NetworkError>)  in
            switch result {
            case .success(let data):
                self.postNotification(data: data)
            case .failure(let error):
                os_log(.error, "\(error.localizedDescription)")
            }
        }
    }
    
    private func postNotification(data: Todoitems) {
        NotificationCenter.default.post(
            name: .didFetchInfo,
            object: self,
            userInfo: [userInfo.taskData:data])
    }
    
    private func addChildViewControllers() {
        let storyBoard = UIStoryboard(name: "Main", bundle: .main)
        guard let todoViewController = storyBoard.instantiateViewController(withIdentifier: "ToDoTableViewController") as? ToDoTableViewController,
              let doingTableViewController = storyBoard.instantiateViewController(withIdentifier: "DoingTableViewController") as? DoingTableViewController,
              let doneTableViewController = storyBoard.instantiateViewController(withIdentifier: "DoneTableViewController") as? DoneTableViewController else { return }
        
        [todoViewController,doingTableViewController,doneTableViewController].forEach {
            addChild($0)
            self.statckView.addArrangedSubview($0.view)
            
            self.logViewContainer.isHidden = true
        }
    }

}


