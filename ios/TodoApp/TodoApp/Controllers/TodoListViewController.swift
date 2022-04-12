//
//  TodoListViewController.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/04.
//

import UIKit
import OSLog

class TodoListViewController: UIViewController {
    // TODO: Todo 모델 정의 & 소유

    @IBOutlet private weak var badgeView: UIView!
    @IBOutlet private weak var tableView: UITableView!
    @IBOutlet private weak var addToDoButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.configureUI()
        self.addDelegates()
    }

    private func addDelegates() {
        tableView.dataSource = self
    }
    
    private func configureUI() {
        self.badgeView.clipsToBounds = true
        self.badgeView.layer.cornerRadius = self.badgeView.frame.size.height / 2
        
        self.addToDoButton.addAction(UIAction(handler: self.editCard(_:)), for: .touchUpInside)
    }
    
    private func editCard(_ action: UIAction) {
    }
}

extension TodoListViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 10
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: ToDoCell.identifier) as? ToDoCell else {
            Logger.view.error("Fail to get a cell instance of TodoCell in \(#function), \(#fileID)")
            fatalError()
        }
        
        return cell
    }
}
