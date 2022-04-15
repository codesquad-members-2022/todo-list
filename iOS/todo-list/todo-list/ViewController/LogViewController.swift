//
//  ActivityRecoderController.swift
//  todo-list
//
//  Created by Jason on 2022/04/05.
//

import Foundation
import UIKit

class LogViewController: UITableViewController {
    @IBOutlet weak var activityTableView: UITableView!
    
    let logViewModel = LogViewModel(logManager: LogManager())
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupLogTableViewCell()
        logViewModel.delegate = self
        logViewModel.load()
        
    }
    
    private func setupLogTableViewCell() {
        activityTableView.register(LogTableViewCell.self, forCellReuseIdentifier: LogTableViewCell.identifier)
    }
    
}
extension LogViewController:LogViewModelDelegate {
    func update() {
        DispatchQueue.main.async {
            self.activityTableView.reloadData()
        }
    }
}

//MARK: - UITableViewDataSource에 관련된 Method
extension LogViewController{
    
    // TableView 행의 개수 반환 - return 값 변경 예정
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return logViewModel.count
    }
    
    // TableView 각 셀에 대한 설정
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LogTableViewCell.identifier) as? LogTableViewCell else { return UITableViewCell() }
        let cellViewModel = logViewModel.get(index: indexPath.row)
        cell.configure(with: cellViewModel)
        
        return cell
    }
    
}
