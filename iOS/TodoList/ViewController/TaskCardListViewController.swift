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
        guard let cell = tableView.dequeueReusableCell(withIdentifier: identifier.taskCardViewCell, for: indexPath) as? TaskCardViewCell else { return UITableViewCell() }
        if let taskCard = taskCardList?.getCard(at: indexPath.row) {
            cell.setTitle(to: taskCard.title)
            cell.setContents(to: taskCard.content)
        }
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            deleteCard(at: indexPath, from: tableView)
        }
    }
    
    func tableView(_ tableView: UITableView, contextMenuConfigurationForRowAt indexPath: IndexPath, point: CGPoint) -> UIContextMenuConfiguration? {
        guard let card = taskCardList?.getCard(at: indexPath.row) else { return nil }
        let id = card.id
        
        let moveToDoneTask = UIAction(title: "완료된 일로 이동") { _ in
            let requestCard = RequestCardData(section: "완료된 일", title: card.title, content: card.content)
            DispatchQueue.global().sync {
                URLManager<TaskCard>.request(api: .put(id, requestCard))
                NotificationCenter.default.post(name: .getTaskBoardData, object: nil)
            }
        }
        let editCard = UIAction(title: "수정하기") { _ in
            NotificationCenter.default.post(name: .editMenuTapped, object: nil, userInfo: [NotificationKeyValue.editTaskData:card])
        }
        let removeCard = UIAction(title: "삭제하기", attributes: .destructive) { _ in
            self.deleteCard(at: indexPath, from: tableView)
        }
        
        return UIContextMenuConfiguration(identifier: nil, previewProvider: nil) { _ in
            UIMenu(title:"", children: [moveToDoneTask, editCard, removeCard])
        }
    }
    
    private func deleteCard(at indexPath: IndexPath, from tableView: UITableView) {
        guard let card = taskCardList?.getCard(at: indexPath.row) else { return }
        let id = card.id
        URLManager<TaskCard>.request(api: .delete(id),completionHandler: {_ in})
        
        self.taskCardList?.remove(at: indexPath.row)
        tableView.deleteRows(at: [indexPath], with: .fade)
        guard let count = taskCardList?.count else { return }
        self.taskCardListView.setCountBadge(with: count)
    }
}
