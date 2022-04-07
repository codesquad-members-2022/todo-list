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
    }
}

extension KanbanViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dummyTitle.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: TaskTableViewCell.identifier) as? TaskTableViewCell else { return UITableViewCell() }
        
        var config = cell.defaultContentConfiguration()
        
        config.attributedText = cell.makePrimaryText(title: dummyTitle[indexPath.row])
        config.secondaryAttributedText = cell.makeSecondaryText(contents: dummyContents[indexPath.row], authored: dummyAuthored)
        
        cell.contentConfiguration = config
        
        var backgroundConfig = UIBackgroundConfiguration.listPlainCell()
        
        backgroundConfig.cornerRadius = 10
        
        cell.backgroundConfiguration = backgroundConfig
        
        return cell
    }
    
    
    func tableView(_ tableView: UITableView, trailingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        let delete = UIContextualAction(style: .destructive, title: "삭제") { action, view, handler in
            print("delete task")
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
        
        let add = UIAlertAction(title: "등록", style: .default) { _ in
            let title = alert.textFields?[0].text ?? "nil"
            let content = alert.textFields?[1].text ?? "nil"
            print("제목: \(title)")
            print("내용: \(content)")
        }
        
        alert.addAction(cancel)
        alert.addAction(add)
        
        present(alert, animated: true)
    }
}
