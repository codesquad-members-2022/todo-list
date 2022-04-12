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
    
    @IBOutlet weak var badgeLabel: UILabel!
    @IBOutlet private weak var badgeView: UIView!
    @IBOutlet private weak var tableView: UITableView!
    @IBOutlet private weak var addToDoButton: UIButton!
    
    // Temporary usage, must be removed in the future
    private var viewModel = Array(repeating: 0, count: 10)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.configureUI()
        self.addDelegates()
    }
    
    private func addDelegates() {
        self.tableView.delegate = self
        self.tableView.dataSource = self
    }
    
    private func configureUI() {
        self.badgeLabel?.text = String(self.viewModel.count)
        self.badgeView.clipsToBounds = true
        self.badgeView.layer.cornerRadius = self.badgeView.frame.size.height / 2
        
        self.addToDoButton.addAction(UIAction(handler: self.editCard(_:)), for: .touchUpInside)
    }
    
    private func editCard(_ action: UIAction) {
    }
}

extension TodoListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: TodoCell.identifier) as? TodoCell else {
            Logger.view.error("Fail to get a cell instance of TodoCell in \(#function), \(#fileID)")
            fatalError()
        }
        
        return cell
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return self.viewModel.count
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 0
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let headerView = UIView()
        headerView.backgroundColor = UIColor.clear
        return headerView
    }
}

extension TodoListViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, editingStyleForRowAt indexPath: IndexPath) -> UITableViewCell.EditingStyle {
        return .delete
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            self.tableView.beginUpdates()
            self.viewModel.remove(at: indexPath.section)
            self.tableView.deleteRows(at: [indexPath], with: .left)
            self.tableView.deleteSections(IndexSet(integer: indexPath.section), with: .left)
            self.badgeLabel?.text = String(self.viewModel.count)
            self.tableView.endUpdates()
        }
    }
}
