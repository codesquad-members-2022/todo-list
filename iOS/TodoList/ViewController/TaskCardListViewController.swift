import UIKit

class TaskCardListViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        setUpStackView()
    }
    
    func setUpStackView() {
        if let taskListView = Bundle.main.loadNibNamed(NameSpace.nib.taskCardListView, owner: nil, options: nil)?.first as? TaskCardListView {
            taskListView.tableView.delegate = self
            taskListView.tableView.dataSource = self
            let nibName = UINib(nibName: NameSpace.nib.taskCardViewCell, bundle: nil)
            taskListView.tableView.register(nibName, forCellReuseIdentifier: NameSpace.identifier.taskCardViewCell)
            self.view.addSubview(taskListView)
            taskListView.translatesAutoresizingMaskIntoConstraints = false
            NSLayoutConstraint.activate([
                taskListView.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 0),
                taskListView.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor, constant: 0),
                taskListView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor, constant: 0),
                taskListView.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor, constant: 0)
            ])
        }
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
