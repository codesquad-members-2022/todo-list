import UIKit

class TaskCardListViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupCell()
    }
        
    private func setupCell() {
        if let taskListView = Bundle.main.loadNibNamed(NameSpace.nib.taskCardListView, owner: self, options: nil)?.first as? TaskCardListView {
            let nibName = UINib(nibName: NameSpace.nib.taskCardViewCell, bundle: nil)
            
            taskListView.table.delegate = self
            taskListView.table.dataSource = self
            taskListView.table.register(nibName, forCellReuseIdentifier: NameSpace.identifier.taskCardViewCell)
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
