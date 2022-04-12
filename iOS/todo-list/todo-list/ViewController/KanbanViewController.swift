//
//  KanbanViewController.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/05.
//

import UIKit

class KanbanViewController: UIViewController {
    
    let dummyTitle = ["Github 공부하기", "테이블 뷰 Dummy Data로 세팅 및 구현", "알고리즘 문제 풀기", "iOS 면담 form 사전 작성"]
    let dummyContents = [
        "Vestibulum ac porttitor nulla.",
        "Proin finibus, lorem ut gravida sodales, nibh enim finibus turpis, vitae convallis lorem dui quis quam. Nunc vitae nunc ante. Aenean dui lectus",
        "Nullam eget nibh at nulla venenatis laoreet eu sit amet risus.",
        "Quisque fringilla magna non diam commodo, ac posuere enim ultrices. Donec a convallis dui. Mauris suscipit ipsum sit amet neque bibendum, ut mattis ante egestas. Morbi commodo nulla eget ante finibus, vestibulum maximus odio dapibus."
    ]
    let dummyAuthored = "Authored by iOS"
    
    @IBOutlet var columns: [UITableView]!
    @IBOutlet var countBadges: [UILabel]!
    @IBOutlet var addButtons: [UIButton]!
    
    
    let columnViewModels = [
        ColumnViewModel(state: .todo, taskManager: TaskManager.shared),
        ColumnViewModel(state: .inProgress, taskManager: TaskManager.shared ),
        ColumnViewModel(state: .done, taskManager: TaskManager.shared)
    ]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        countBadges.forEach { badge in
            badge.layer.cornerRadius = badge.layer.bounds.width / 2
            badge.clipsToBounds = true
            badge.text = "\(dummyTitle.count)"
        }
        
        columns.forEach { column in
            column.register(TaskTableViewCell.self, forCellReuseIdentifier: TaskTableViewCell.identifier)
        }
        
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
        let delete = UIContextualAction(style: .destructive, title: "삭제") { action, view, handler in
            // delete task
            handler(true)
        }
        
        return UISwipeActionsConfiguration(actions: [delete])
    }
}

// MARK: - Use Case: Add

extension KanbanViewController {
    
    @IBAction func AddButtonTouched(_ sender: UIButton) {
        let alert = UIAlertController(title: "새로운 카드 추가", message: nil, preferredStyle: .alert)
        
        alert.addTextField { titleTextField in
            titleTextField.font = UIFont.boldSystemFont(ofSize: 14)
            titleTextField.placeholder = "제목을 입력하세요"
        }
        
        alert.addTextField { contentTextField in
            contentTextField.placeholder = "내용을 입력하세요"
        }
        
        let cancel = UIAlertAction(title: "취소", style: .destructive) { _ in
            print("취소")
        }
        
        let add = UIAlertAction(title: "등록", style: .default) { [weak self] _ in
            
            guard let columnIndex = self?.addButtons.firstIndex(where: { $0 === sender }), let viewModel = self?.columnViewModels[columnIndex] else { return }

            
            let title = alert.textFields?[0].text ?? "nil"
            let content = alert.textFields?[1].text ?? "nil"
            viewModel.add(title: title, content: content)
            
        }
        
        alert.addAction(cancel)
        alert.addAction(add)
        
        present(alert, animated: true)
    }
}
