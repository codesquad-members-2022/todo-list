//
//  MainViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/04.
//

import UIKit

class MainViewController: UIViewController {
    
    @IBOutlet weak var statckView: UIStackView!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        self.title = "TO-DO-LIST"
        self.navigationController?.navigationBar.backgroundColor = .darkGray
        
        addChildViewControllers()
        
}
    
    private func addChildViewControllers() {
        let storyBoard = UIStoryboard(name: "Main", bundle: .main)
        guard let todoViewController = storyBoard.instantiateViewController(withIdentifier: "ToDoTableViewController") as? ToDoTableViewController,
              let doingTableViewController = storyBoard.instantiateViewController(withIdentifier: "DoingTableViewController") as? DoingTableViewController,
              let doneTableViewController = storyBoard.instantiateViewController(withIdentifier: "DoneTableViewController") as? DoneTableViewController else { return }
        
        [todoViewController,doingTableViewController,doneTableViewController].forEach {
            addChild($0)
            self.statckView.addArrangedSubview($0.view)
        }
    }
}
