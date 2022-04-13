import UIKit

class TaskCardListView: UIView {
    @IBOutlet private weak var table: UITableView!
    @IBOutlet private weak var title: UILabel!
    @IBOutlet private weak var taskCountBadge: UILabel!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        NotificationCenter.default.addObserver(forName: .postInputData, object: nil, queue: .main, using: postInputData(noti:))
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        NotificationCenter.default.addObserver(forName: .postInputData, object: nil, queue: .main, using: postInputData(noti:))
    }
    
    func setTableView(with viewController: UIViewController & UITableViewDelegate & UITableViewDataSource) {
        self.table.delegate = viewController
        self.table.dataSource = viewController
        let nibName = UINib(nibName: NameSpace.nib.taskCardViewCell, bundle: nil)
        self.table.register(nibName, forCellReuseIdentifier: NameSpace.identifier.taskCardViewCell)

    }

    @IBAction func addTaskButtonTapped(_ sender: UIButton) {
        NotificationCenter.default.post(name: .addTaskButtonTapped, object: title)
    }
    
    func setTitle(to text: String) {
        self.title.text = text
    }
    
    func setCountBadge(with count: Int) {
        self.taskCountBadge.text = String(count)
    }
    
    func postInputData(noti: Notification) {
        guard let cardData = noti.object as? (String) -> RequestCardData else { return }
        let card = cardData(self.title.text ?? "")
        NotificationCenter.default.post(name: .editButtonTapped, object: nil, userInfo: [NotificationKeyValue.postTaskData:card])
    }
    
    func reloadTable() {
        DispatchQueue.main.async {
            self.table.reloadData()
        }
    }
}

