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
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.register(LogViewCell.self, forCellReuseIdentifier: "LogViewCell")
        return tableView
    }()
    
    private let devider: UILabel = {
        let label = UILabel()
        label.backgroundColor = .black
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private let closeButton: UIButton = {
        let button = UIButton()
        button.setImage(UIImage(named: "ic_close"), for: .normal)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    let logViewModel = LogViewModel()
    var count: Int = 0
    var loadData = [ActivityLog]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bind()
        attribute()
        layout()
        loadData = logViewModel.loadData()
    }
    
    private func bind(){
        tableView.delegate = self
        tableView.dataSource = self
        closeButton.addAction(UIAction(handler: { _ in self.view.isHidden = true }), for: .touchUpInside)
    }
    
    private func attribute(){
        view.backgroundColor = .white
    }
    
    private func layout(){
        view.addSubview(tableView)
        view.addSubview(devider)
        view.addSubview(closeButton)
    
        NSLayoutConstraint.activate([
            tableView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 80),
            tableView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 48),
            tableView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -48),
            tableView.bottomAnchor.constraint(equalTo: view.bottomAnchor),
            
            devider.topAnchor.constraint(equalTo: view.topAnchor),
            devider.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            devider.heightAnchor.constraint(equalTo: view.heightAnchor),
            devider.widthAnchor.constraint(equalToConstant: 0.5),
            
            closeButton.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 24),
            closeButton.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -48),
            closeButton.widthAnchor.constraint(equalToConstant: 24),
            closeButton.heightAnchor.constraint(equalToConstant: 24)
        ])
    }
}


extension LogViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        return loadData.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "LogViewCell", for: indexPath) as? LogViewCell else { return UITableViewCell() }
        cell.inputData(loadData[indexPath.item])
        return cell
    }
}


