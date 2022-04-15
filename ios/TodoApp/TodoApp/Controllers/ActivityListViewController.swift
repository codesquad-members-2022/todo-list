//
//  ActivityTableViewController.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/05.
//

import UIKit
import OSLog

class ActivityListViewController: UITableViewController {
    
    var activities: Activities = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.configureTableView()
        ActivityRepository.getData(resource: "http://3.38.240.18:8080/history")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        configureNotifications()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self)
    }
    
    private func configureTableView() {
        self.tableView.register(ActivityCell.self, forCellReuseIdentifier: ActivityCell.identifier)
        self.tableView.showsVerticalScrollIndicator = false
        self.tableView.allowsSelection = false
        self.tableView.separatorStyle = .none
    }

    // MARK: - Table view data source
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return activities.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(
            withIdentifier: ActivityCell.identifier,
            for: indexPath
        ) as? ActivityCell else {
            Logger.view.error("Fail to get a cell instance of ActivityCell in \(#function), \(#fileID)")
            fatalError()
        }

        let item = activities[indexPath.row]
        let factory = FieldFactory()
        let body = factory.make(.body, item)
        let footer = factory.make(.footer, item)
        cell.setBodyText(body.text)
        cell.setFooterText(footer.text)
        return cell
    }
}

// MARK: - NotificationCenter Configuration
extension ActivityListViewController {
    private func configureNotifications() {
        NotificationCenter.default.addObserver(self, selector: #selector(updateActivities(notification:)), name: Notification.Name.successfulFetch, object: ActivityRepository.self)
        NotificationCenter.default.addObserver(self, selector: #selector(informErrorStatus(notification:)), name: Notification.Name.failedFetch, object: ActivityRepository.self)
    }
        
    @objc func updateActivities(notification: Notification) {
        guard let activities = notification.userInfo?[NotificationKey.activity] as? Activities else { return }
        self.activities = activities
    }
    
    @objc func informErrorStatus(notification: Notification) {
        DispatchQueue.main.async {
            Alert.showNetworkAlert(on: self, with: "네트워크 장애", message: "서버로부터 데이터를 불러오는데 실패했습니다.")
        }
    }
}
