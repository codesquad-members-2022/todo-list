//
//  LogViewController.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/07.
//

import UIKit

class LogViewController: UIViewController {
    
    private let tableView: UITableView = {
        let tableView = UITableView()
        return tableView
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(LogViewCell.self, forCellReuseIdentifier: "LogViewCell")
        setConstraint()
    }
    
    private func setConstraint(){
        view.addSubview(tableView)
        tableView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            tableView.topAnchor.constraint(equalTo: view.topAnchor, constant: 72),
            tableView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 48),
            tableView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -48),
            tableView.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant: 0)
        ])
    }
}

extension LogViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        return 4
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "LogViewCell", for: indexPath) as? LogViewCell else { return UITableViewCell() }
        return cell
    }
}


