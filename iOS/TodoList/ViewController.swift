import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var taskListStackView: UIStackView!
    private let todoViewController = TaskListBoardViewController()
    private let doingViewController = TaskListBoardViewController()
    private let doneViewController = TaskListBoardViewController()
    override func viewDidLoad() {
        super.viewDidLoad()
        addChild(todoViewController)
        addChild(doingViewController)
        addChild(doneViewController)
        todoViewController.add()
        layout()
    }
    
    private func layout() {
        self.taskListStackView.translatesAutoresizingMaskIntoConstraints = false
        
        self.children.forEach{
            self.taskListStackView.addArrangedSubview($0.view)
        }
                
        self.taskListStackView.axis = .horizontal
        self.taskListStackView.alignment = .fill
        self.taskListStackView.distribution = .fillEqually
        self.taskListStackView.spacing = 22
    }

}


    
