import UIKit

class TodoTableView: UITableView {
    
    var management: TodoTableViewManagement? {
        didSet {
            delegate = management
            dataSource = management
        }
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        register(TodoTableViewCell.nib, forCellReuseIdentifier: TodoTableViewCell.cellName)
    }
    
    override init(frame: CGRect, style: UITableView.Style) {
        super.init(frame: frame, style: style)
        register(TodoTableViewCell.nib, forCellReuseIdentifier: TodoTableViewCell.cellName)
    }
    
    func getDataCount() -> Int {
        return management?.dataSource.count ?? 0
    }
}

class TodoTableViewManagement: NSObject {
    
    var targetTableView: UITableView
    var todoBoard: TodoBoard
    
    private(set) var dataSource = [CardData]() {
        didSet {
            DispatchQueue.main.async { [weak self] in
                guard let self = self else { return }
                self.targetTableView.reloadData()
            }
        }
    }
    
    init(_ tableView: UITableView, in board: TodoBoard) {
        targetTableView = tableView
        todoBoard = board
        super.init()
    }
    
    func setTableView(_ tableView: UITableView) {
        targetTableView = tableView
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
