import UIKit

class TaskListBoardViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setUpStackView()
                
    }
    
    func setUpStackView() {
        var taskViewList: [TaskListView] = []
        
        for _ in 0...2 {
            let taskListView = TaskListView(frame: CGRect())
            taskViewList.append(taskListView)
        }
        
        let stackView = UIStackView(arrangedSubviews: taskViewList)
        
        stackView.translatesAutoresizingMaskIntoConstraints = false
        
        stackView.axis = .horizontal
        stackView.alignment = .fill
        stackView.distribution = .fill
        stackView.spacing = 10
        
        view.addSubview(stackView)
        
        NSLayoutConstraint.activate([
            stackView.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 10),
            stackView.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor, constant: 10),
            stackView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor, constant: -10),
            stackView.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor, constant: 10)
        ])

    }


}
