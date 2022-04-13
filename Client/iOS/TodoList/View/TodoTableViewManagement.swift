import UIKit

class TodoTableViewManagement: NSObject {
    
    var todoTableView: TodoTableView
    var todoBoard: TodoBoard
    
    private(set) var dataSource = [CardData]()
    
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
    
    @discardableResult
    func setDataSource(at index: Int, data: CardData) -> Bool{
        guard 0 <= index, index < dataSource.count else {
            return false
        }
        dataSource[index] = data
        return true
    }
    
    func setDataSource(data: [CardData]) {
        dataSource = data
        DispatchQueue.main.async { [weak self] in
            
            guard let self = self else {
                return
            }
            
            self.todoTableView.reloadData()
        }
    }
}

extension TodoTableViewManagement: UITableViewDataSource {

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        dataSource.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: TodoTableViewCell.cellName, for: indexPath) as? TodoTableViewCell else {
            return UITableViewCell()
        }

        cell.reloadAllLabels(dataSource[indexPath.row])

        return cell
    }
}

extension TodoTableViewManagement: UITableViewDelegate { }
