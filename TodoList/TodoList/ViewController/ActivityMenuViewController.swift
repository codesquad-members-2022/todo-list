//
//  MenuViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/11.
//

import UIKit

final class ActivityMenuViewController: UIViewController{
    var activityView = ActivityMenuView()
    let cellIdentifier = "MenuActivityCell"
    
    let activityLog = [["@푸코","HTML/CSS공부하기를 해야할 일에서 하고 있는 일로 이동하였습니다.","1분 전"],
                       ["@푸코","해야할 일에 HTML/CSS공부하기를 등록하였습니다.","1분 전"],
                       ["@푸코","해야할 일에 블로그에 포스팅할 것을 등록하였습니다","1분 전"],
                       ["@푸코","해야할 일에 GitHub 공부하기를 등록하였습니다","1분 전"]]
    
    override func viewDidLoad(){
        super.viewDidLoad()
        self.view = activityView
        activityView.tableView.delegate = self
        activityView.tableView.dataSource = self
        activityView.tableView.register(MenuActivityCell.classForCoder(), forCellReuseIdentifier: cellIdentifier)
    }
}


extension ActivityMenuViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        return activityLog.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell{
        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier) as? MenuActivityCell else { return UITableViewCell()}
        let data = activityLog[indexPath.row]
        cell.setLabelText(author: data[0], content: data[1], time: data[2])
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat{
        return UITableView.automaticDimension
    }
}
