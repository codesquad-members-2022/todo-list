import UIKit

class TaskCardListViewController: UIViewController {
    
    @IBOutlet var taskCardList: TaskCardListView!
    override func viewDidLoad() {
        super.viewDidLoad()
        setupCell()
    }
    
    private func setupCell() {
        self.taskCardList.setTableView(with: self)
        self.taskCardList.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.taskCardList.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 0),
            self.taskCardList.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor, constant: 0),
            self.taskCardList.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor, constant: 0),
            self.taskCardList.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor, constant: 0)
        ])
        
    }
    
}

extension TaskCardListViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 15
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: NameSpace.identifier.taskCardViewCell, for: indexPath) as? TaskCardViewCell else { return UITableViewCell() }
        return cell
    }
    
}
