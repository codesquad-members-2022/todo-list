import UIKit

class TodoTableView: UITableView {
    
    /// TodoTableView가 사용하길 의도한 TodoRepositoryRespondWrapper가 delegate, datasource를 쓸 수 있도록 하는 내부 프로퍼티.
    var management: TodoRepositoryRespondWrapper? {
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
}
