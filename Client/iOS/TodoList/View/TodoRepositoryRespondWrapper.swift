import UIKit

protocol TodoBadgeDelegate {
    func setBadgeCount(_ count: Int)
}

class TodoRepositoryRespondWrapper: NSObject {
    
    var todoTableView: TodoTableView
    var todoBoard: TodoBoard
    
    private(set) var dataSource = [CardData]()
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
                let result = info["result"] as? Result<[CardData], DataTaskError>
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
    
    func insertDataSource(data: CardData, at index: Int) {
        guard isEnableIndex(index) else { return }
        dataSource[index] = data
        reloadTodoTableView()
    }
    
    func setDataSource(data: [CardData]) {
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
        dataSource.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: TodoTableViewCell.cellName, for: indexPath) as? TodoTableViewCell else {
            return UITableViewCell()
        }

        cell.applyTextAllLabels(data: dataSource[indexPath.row])

        return cell
    }
}

extension TodoRepositoryRespondWrapper: UITableViewDelegate { }
