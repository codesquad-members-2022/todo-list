//
//  LogTableViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/04.
//

import UIKit

class LogViewController: UIViewController {
        
    weak private var tableView: BoardTableView<Todo,CardCell>!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .gray
        setTableView()
    }

    private func setTableView() {

    }    
    @IBAction func test(_ sender: Any) {

        guard let mainVC = self.parent as? MainViewController else{ return }
        mainVC.TapCloseLogViewButton()
    }
}

// MARK: - Table view data source
extension LogViewController : UITableViewDelegate , UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        guard let list = list else { return 0 }
//        self.header.updateCount(list.count)
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LogCell.identifier) as? LogCell else { return UITableViewCell() }
//        guard let list = self.list else { return UITableViewCell() }
//        cell.loadCardInfo(info: list[indexPath.row])
        return cell
    }
    
}
