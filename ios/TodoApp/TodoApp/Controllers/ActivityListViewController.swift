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
        NetworkHandler.getData(resource: "https://1dc4c2f3-00b4-446d-a22a-d6920eaee622.mock.pstmn.io/history")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NotificationCenter.default.addObserver(self, selector: #selector(getActivities(notification:)), name: Notification.Name.fetch, object: NetworkHandler.self)     // 문제 생기면 object 를 nil 로 바꿔서 작업해볼것
    }
    
    private func configureTableView() {
        self.tableView.register(ActivityCell.self, forCellReuseIdentifier: ActivityCell.identifier)
        self.tableView.showsVerticalScrollIndicator = false
        self.tableView.allowsSelection = false
        self.tableView.separatorStyle = .none
    }

    // MARK: - Table view data source
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard !activities.isEmpty else { return 0 }
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
        // TODO: - action의 종류에 따라 bodyString의 형식이 조금씩 변경되어야한다.
        // TODO: - 특정 단어 강조를 ActivityListVC 에서 할지, ActivityCell 에서 할지 갈피를 잡아야한다.
        // TODO: - "~ 분전"의 dataFommater 형식 설정해야한다.
        let item = activities[indexPath.row]
        var bodyString = ""
        var footerString = item.modifiedAt
        switch item.action {
        case "ADD":
            bodyString += String(item.newColumn)
            bodyString += "에 "
            bodyString += String(item.id)
            bodyString += "을 등록하였습니다."
        case "UPDATE":
            bodyString += String(item.newColumn)
            bodyString += "에 "
            bodyString += String(item.id)
            bodyString += "을 수정하였습니다."
        case "MOVE":
            bodyString += String(item.id)
            bodyString += "을 "
            bodyString += String(item.oldColumn)
            bodyString += "에서 "
            bodyString += String(item.newColumn)
            bodyString += "로 "
            bodyString += "을 이동하였습니다."
        case "DELETE":
            bodyString += String(item.newColumn)
            bodyString += "에 "
            bodyString += String(item.id)
            bodyString += "을 삭제하였습니다."
        default:
            Logger.view.error("bodyString이 입력되지 않았습니다.")
        }
        
        cell.setBodyText(bodyString)
        cell.setFooterText(footerString)
        return cell
    }
}

extension ActivityListViewController {
    @objc func getActivities(notification: Notification) {
        guard let activities = notification.userInfo?[NotificationKey.activity] as? Activities else { return }
        self.activities = activities
    }
}
