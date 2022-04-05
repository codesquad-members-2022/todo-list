import UIKit

class TaskListBoardViewController: UIViewController {
    var foo: [UIColor] = [.red,.gray,.green,.brown]
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpStackView()
    }

    func setUpStackView() {
        var taskViewList: [TaskListView] = []
        
        for _ in 0...2 {
            if let taskListView = Bundle.main.loadNibNamed("TaskListView", owner: nil, options: nil)?.first as? TaskListView {
                taskListView.tableView.delegate = self
                taskListView.tableView.dataSource = self
                taskListView.tableView.rowHeight = UITableView.automaticDimension
                taskListView.tableView.estimatedRowHeight = 400
                let nibName = UINib(nibName: "TaskCardViewCell", bundle: nil)
                taskListView.tableView.register(nibName, forCellReuseIdentifier: "cardCell")
                taskListView.frame = self.view.bounds
                taskViewList.append(taskListView)
            }
            
        }
        
        let stackView = UIStackView(arrangedSubviews: taskViewList)
        
        stackView.translatesAutoresizingMaskIntoConstraints = false
        
        stackView.axis = .horizontal
        stackView.alignment = .fill
        stackView.distribution = .fillEqually
        stackView.spacing = 22
        
        
        view.addSubview(stackView)

        NSLayoutConstraint.activate([
            stackView.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 10),
            stackView.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor, constant: 10),
            stackView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor, constant: -10),
            stackView.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor, constant: -10)
        ])

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
