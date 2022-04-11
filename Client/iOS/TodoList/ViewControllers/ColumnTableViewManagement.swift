//
// Created by 백상휘 on 2022/04/11.
//

import UIKit

class ColumnTableViewManagement: NSObject {
    lazy var dataSource = [CardData]() {
        didSet {
            DispatchQueue.main.async { [weak self] in
                guard let self = self else { return }
                self.targetTableView.reloadData()
            }
        }
    }

    private var targetTableView: UITableView!

    convenience init(tableView: UITableView) {
        self.init()
        targetTableView = tableView
    }
}

extension ColumnTableViewManagement: UITableViewDataSource {

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        dataSource.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: TodoTableViewCell.cellName, for: indexPath) as? TodoTableViewCell else {
            return UITableViewCell()
        }

        cell.admitCardData(dataSource[indexPath.row])

        return cell
    }
}

extension ColumnTableViewManagement: UITableViewDelegate {

}