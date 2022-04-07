import UIKit

class TaskListBoardViewController: UIViewController {
    var foo: [UIColor] = [.red,.gray,.green,.brown]
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpStackView()
    }
    
    func setUpStackView() {
        if let taskListView = Bundle.main.loadNibNamed(TaskListView.nibName, owner: nil, options: nil)?.first as? TaskListView {
            taskListView.tableView.delegate = self
            taskListView.tableView.dataSource = self
            let nibName = UINib(nibName: TaskCardViewCell.nibName, bundle: nil)
            taskListView.tableView.register(nibName, forCellReuseIdentifier: TaskCardViewCell.identifier)
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
    
    func add() {
        self.foo.append(UIColor.brown)
    }
}
           




extension TaskListBoardViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.foo.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "cardCell", for: indexPath) as! TaskCardViewCell
        return cell
    }
    
}
