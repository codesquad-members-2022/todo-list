import UIKit

protocol TodoBadgeDelegate {
    func setBadgeCount(_ count: Int)
}

class TodoRepositoryRespondWrapper: NSObject {
    
    var todoTableView: TodoTableView
    var todoBoard: TodoBoard
    
    private(set) var dataSource = [Card]()
    var badgeDelegate: TodoBadgeDelegate?
    
    init(_ tableView: TodoTableView, in board: TodoBoard) {
        todoTableView = tableView
        todoBoard = board
        
        super.init()
        
        todoTableView.management = self
        
        NotificationCenter.default.addObserver(
            forName: todoBoard.notificationName,
            object: AppDelegate.middleWare,
            queue: .main)
        { [weak self] noti in
            
            guard
                let info = noti.userInfo,
                let result = info["result"] as? Result<[Card], DataTaskError>
            else {
                return
            }
            
            switch result {
            case .success(let data):
                guard let self = self else { return }
                self.setDataSource(data: data)
            case .failure(let error):
                Log.error(error)
            }
        }
    }
    
    func setTableView(_ tableView: TodoTableView) {
        todoTableView = tableView
    }
    
    private func isEnableIndex(_ index: Int) -> Bool {
        return (0..<dataSource.count ~= index)
    }
    
    func insertDataSource(data: Card, at index: Int) {
        guard isEnableIndex(index) else { return }
        dataSource[index] = data
        reloadTodoTableView()
    }
    
    func setDataSource(data: [Card]) {
        dataSource = data
        reloadTodoTableView()
    }
    
    func reloadTodoTableView() {
        DispatchQueue.main.async { [weak self] in
            guard let self = self else { return }
            self.todoTableView.reloadData()
        }
        
        self.badgeDelegate?.setBadgeCount(self.dataSource.count)
    }
}

extension TodoRepositoryRespondWrapper: UITableViewDataSource {

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        let result = (dataSource.count * 2) - 1
        return result < 0 ? 0 : result
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        if 0 < indexPath.row, indexPath.row.isMultiple(of: 2) == false {
            let cell = UITableViewCell()
            cell.heightAnchor.constraint(equalToConstant: 16).isActive = true
            cell.contentView.backgroundColor = UIColor(named: "gray6")
            return cell
        }
        
        guard let cell = tableView.dequeueReusableCell(withIdentifier: TodoTableViewCell.cellName, for: indexPath) as? TodoTableViewCell else {
            return UITableViewCell()
        }

        cell.applyTextAllLabels(data: dataSource[indexPath.row / 2])
        cell.isUserInteractionEnabled = true
        return cell
    }
}

extension TodoRepositoryRespondWrapper: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, trailingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        
        if let cell = tableView.cellForRow(at: indexPath) as? TodoTableViewCell {
            cell.roundedView.layer.maskedCorners = [.layerMinXMaxYCorner, .layerMinXMinYCorner]
        }
        
        let action = UIContextualAction(
            style: .destructive,
            title: "Delete")
        { [weak self] action, view, completion in
            
            guard let self = self else { return }
            
            AppDelegate.middleWare.deleteCard(id: self.dataSource[indexPath.row/2].id, at: self.todoBoard)
            completion(true)
            action.backgroundColor = .red
        }
        
        return UISwipeActionsConfiguration(actions: [action])
    }
    
    func tableView(_ tableView: UITableView, didEndEditingRowAt indexPath: IndexPath?) {
        if let indexPath = indexPath, let cell = tableView.cellForRow(at: indexPath) as? TodoTableViewCell {
            cell.roundedView.layer.maskedCorners = [.layerMinXMaxYCorner, .layerMinXMinYCorner, .layerMaxXMaxYCorner, .layerMaxXMinYCorner]
        }
    }
}
