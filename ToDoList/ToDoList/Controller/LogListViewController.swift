import UIKit

class LogListViewController: UIViewController {
    
    private let logManager: LogManager
    private var mainViewController: MainViewController? = nil
    
    @IBOutlet weak var tableView: UITableView!
    
    init(coder: MainViewController) {
        self.logManager = LogManager()
        self.mainViewController = coder
        super.init(nibName: "LogListView", bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        self.logManager = LogManager()
        super.init(coder: coder)
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.dataSource = self
        self.tableView.register(UINib(nibName: LogListViewCell.identifier, bundle: nil), forCellReuseIdentifier: LogListViewCell.identifier)
    }

    @IBAction func didClickCloseButton(_ sender: Any) {
        UIView.animate(withDuration: 0.5) {
            self.view.isHidden = self.view.isHidden == true ? false : true
        }
    }
}

extension LogListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.logManager.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LogListViewCell.identifier, for: indexPath) as? LogListViewCell else {
            return UITableViewCell()
        }
        
        let newLog = logManager[indexPath.item]
        
        cell.setUserName(name: newLog.userName)
        cell.setBody(body: newLog.body)
        cell.setTimeStamp(time: "1분 전")
        
        return cell
    }

}
