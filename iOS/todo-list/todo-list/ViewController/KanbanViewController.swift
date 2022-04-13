//
//  KanbanViewController.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/05.
//

import UIKit

class KanbanViewController: UIViewController {
    
    @IBOutlet var columns: [UITableView]!
    @IBOutlet var countBadges: [UILabel]!
    @IBOutlet var addButtons: [UIButton]!
    
    let columnViewModels = [
        ColumnViewModel(state: .todo, taskManager: TaskManager.shared),
        ColumnViewModel(state: .inProgress, taskManager: TaskManager.shared),
        ColumnViewModel(state: .done, taskManager: TaskManager.shared)
    ]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupBadges()
        setupTableViewCell()
        setupViewModel()
    }
    
    private func setupBadges() {
        countBadges.forEach { badge in
            badge.layer.cornerRadius = badge.layer.bounds.width / 2
            badge.clipsToBounds = true
        }
    }
    
    private func setupTableViewCell() {
        columns.forEach { column in
            column.register(TaskTableViewCell.self, forCellReuseIdentifier: TaskTableViewCell.identifier)
        }
    }
    
    private func setupViewModel() {
        columnViewModels.enumerated().forEach { [weak self] (index, viewModel) in
            viewModel.list.bind { taskVMs in
                DispatchQueue.main.async {
                    self?.columns[index].reloadData()
                    self?.countBadges[index].text = "\(taskVMs.count)"
                }
            }
            viewModel.load()
        }
    }
}

extension KanbanViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard let columnIndex = columns.firstIndex(where: { $0 === tableView }) else { return 0 }
        
        return columnViewModels[columnIndex].count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: TaskTableViewCell.identifier) as? TaskTableViewCell,
              let columnIndex = columns.firstIndex(where: { $0 === tableView }),
              let cellVM = columnViewModels[columnIndex][indexPath.row] else {
            return UITableViewCell()
        }
        
        cell.configure(with: cellVM)
        
        return cell
    }
    
    
    func tableView(_ tableView: UITableView, trailingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        
        guard let columnIndex = columns.firstIndex(where: { $0 === tableView }), let taskId = columnViewModels[columnIndex][indexPath.row]?.id else {
            return UISwipeActionsConfiguration(actions: [])
        }
        
        let columnVM = columnViewModels[columnIndex]
        
        let delete = UIContextualAction(style: .destructive, title: "삭제") { action, view, handler in
            columnVM.delete(id: taskId)
            handler(true)
        }
        
        return UISwipeActionsConfiguration(actions: [delete])
    }
}

// MARK: - Use Case: Add Task

extension KanbanViewController {
    
    @IBAction func AddButtonTouched(_ sender: UIButton) {
        let alert = UIAlertController(title: "새로운 카드 추가", message: nil, preferredStyle: .alert)
        
        alert.addTextField { titleTextField in
            titleTextField.font = TaskTableViewCell.Font.title
            titleTextField.placeholder = "제목을 입력하세요"
        }
        
        alert.addTextField { contentTextField in
            contentTextField.font = TaskTableViewCell.Font.content
            contentTextField.placeholder = "내용을 입력하세요"
        }
        
        let cancel = UIAlertAction(title: "취소", style: .destructive) { [weak self] _ in
            self?.dismiss(animated: true)
        }
        
        let add = UIAlertAction(title: "등록", style: .default) { [weak self] _ in
            guard let columnIndex = self?.addButtons.firstIndex(where: { $0 === sender }),
                  let viewModel = self?.columnViewModels[columnIndex] else { return }
            
            let title = alert.textFields?[0].text ?? "nil"
            let content = alert.textFields?[1].text ?? "nil"
            
            viewModel.add(title: title, content: content)
        }
        
        alert.addAction(cancel)
        alert.addAction(add)
        
        present(alert, animated: true)
    }
}
