import UIKit

class TaskCardListViewController: UIViewController {
    
    @IBOutlet var taskCardListView: TaskCardListView!
    
    private var taskCardList: TaskCardList?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupCell()
    }
    
    private func setupCell() {
        self.taskCardListView.setTableView(with: self)
        self.taskCardListView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.taskCardListView.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 0),
            self.taskCardListView.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor, constant: 0),
            self.taskCardListView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor, constant: 0),
            self.taskCardListView.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor, constant: 0)
        ])
        
    }
    
    func set(_ title: String, with cards: [TaskCard]) {
        self.taskCardList = TaskCardList(sectionTitle: title, cards: cards)
        let count = self.taskCardList?.count ?? 0
        
        self.taskCardListView.setTitle(to: title)
        self.taskCardListView.setCountBadge(with: count)
        self.taskCardListView.reloadTable()
    }
    
}

extension TaskCardListViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard let taskCardList = self.taskCardList else {return 0}
        return taskCardList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: NameSpace.identifier.taskCardViewCell, for: indexPath) as? TaskCardViewCell else { return UITableViewCell() }
        if let taskCard = taskCardList?.getCard(at: indexPath.row) {
            cell.setTitle(to: taskCard.title)
            cell.setContents(to: taskCard.content)
        }
        
        return cell
    }
    
}
