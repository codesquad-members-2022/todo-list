//
//  ActivityTableViewController.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/05.
//

import UIKit

class ActivityListViewController: UITableViewController {
    // TODO: Activity 모델 정의 & 소유
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.configureTableView()
    }
    
    private func configureTableView() {
        self.tableView.register(ActivityCell.self, forCellReuseIdentifier: ActivityCell.identifier)
        self.tableView.showsVerticalScrollIndicator = false
        self.tableView.allowsSelection = false
        self.tableView.separatorStyle = .none
    }

    // MARK: - Table view data source
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 10
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(
            withIdentifier: ActivityCell.identifier,
            for: indexPath
        ) as? ActivityCell else {
            fatalError()
        }
        
        // TODO: 모델에서 데이터 가져와 셀 텍스트 변경
        
        return cell
    }
    
    
}
