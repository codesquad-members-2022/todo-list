//
//  LogTableViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/04.
//

import UIKit

class LogViewController: UIViewController {
        
    private var tableView: BoardTableView<Log,LogCell>!
    private var log: [Log]?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .secondarySystemBackground
//        self.view.layer.borderWidth = 1
        setTableView()
    }

    private func setTableView() {
        self.tableView = BoardTableView(frame: self.view.frame, style: .plain, list: [], cellConfigurator: { model, cell in
//            cell.loadCardInfo(info: model)
        })
        self.view.addSubview(self.tableView)
        tableViewConstraints()
    }    
    
    @IBAction func test(_ sender: Any) {
        guard let mainVC = self.parent as? MainViewController else{ return }
        mainVC.TapCloseLogViewButton()
    }

}


extension LogViewController {
    
    private func tableViewConstraints() {
        NSLayoutConstraint.activate([
            self.tableView.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 35),
            self.tableView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),
            self.tableView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            self.tableView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor)
        ])
    }
}
