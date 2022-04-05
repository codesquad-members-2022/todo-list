//
//  KanbanViewController.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/05.
//

import UIKit

class KanbanViewController: UIViewController {

    let dummy = ["Github 공부하기", "테이블 뷰 Dummy Data로 세팅 및 구현", "알고리즘 문제 풀기", "iOS 면담 form 사전 작성"]
    let columnTitle = ["해야할 일", "하고 있는 일", "완료한 일"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
//        setupUI()
        
        todoTableView.register(TaskTableViewCell.self, forCellReuseIdentifier: "taskTableViewCell")
    }
    
    private func setupUI() {
        let header = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 30))
        header.backgroundColor = .white
        todoTableView.tableHeaderView = header
        
        let headerLabel = UILabel()
        headerLabel.text = columnTitle[0]
        header.addSubview(headerLabel)
        headerLabel.translatesAutoresizingMaskIntoConstraints = false
        headerLabel.widthAnchor.constraint(equalToConstant: headerLabel.intrinsicContentSize.width).isActive = true
        headerLabel.leadingAnchor.constraint(equalTo: header.leadingAnchor, constant: 16).isActive = true
//        headerLabel.heightAnchor.constraint(equalTo: header.heightAnchor).isActive = true
//        headerLabel.centerXAnchor.constraint(equalTo: header.centerXAnchor).isActive = true
        
        let countBadge = UILabel()
        countBadge.text = "1"
        countBadge.backgroundColor = .systemRed
        countBadge.textAlignment = .center
        
        header.addSubview(countBadge)
        countBadge.translatesAutoresizingMaskIntoConstraints = false
        countBadge.leadingAnchor.constraint(equalTo: headerLabel.trailingAnchor, constant: 16).isActive = true
        countBadge.centerXAnchor.constraint(equalTo: headerLabel.centerXAnchor).isActive = true
        countBadge.widthAnchor.constraint(greaterThanOrEqualToConstant: 26).isActive = true
        countBadge.heightAnchor.constraint(equalTo: countBadge.widthAnchor).isActive = true
        
    }
    
    @IBOutlet weak var todoTableView: UITableView!
    @IBOutlet weak var doingTableView: UITableView!
    @IBOutlet weak var doneTableView: UITableView!
    
    
    
}

extension KanbanViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dummy.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "taskTableViewCell") as? TaskTableViewCell else { return UITableViewCell() }
        var config = cell.defaultContentConfiguration()
        config.text = dummy[indexPath.row]
        cell.contentConfiguration = config
        return cell
    }
}

extension KanbanViewController: UITableViewDataSource {
    
}
