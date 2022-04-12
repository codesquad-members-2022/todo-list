import UIKit

class MyTaskViewController: UIViewController {
    @IBOutlet private weak var taskBoardView: TaskBoardView!
    @IBOutlet private weak var actionBoard: ActionBoardView!
    
    @IBOutlet private weak var actionFlowButton: UIButton!
    @IBOutlet private weak var stackViewTrailing: NSLayoutConstraint!
    
    private var taskBoard: TaskBoard?

    override func viewDidLoad() {
        super.viewDidLoad()
        observe()
        URLManager.getTasks()
        resizeConstant()
        actionBoard.moveView()
    }
    
    private func observe() {
        NotificationCenter.default.addObserver(forName: .getTaskBoardData, object: nil, queue: .main, using: setChild(with:))
        NotificationCenter.default.addObserver(forName: .addTaskButtonTapped, object: nil, queue: .main) { _ in
            self.editCardButtonTapped()
        }
    }
    
    
    func setChild(with notification: Notification) {
        self.taskBoard = notification.userInfo?[NotificationKeyValue.getTaskData] as? TaskBoard
        guard let taskBoard = self.taskBoard else {return}
        for (key,value) in taskBoard.lists {
            let controller = TaskCardListViewController()
            controller.set(key, with: value)
            self.addChild(controller)
            self.taskBoardView.addArrangedSubview(controller.view)
        }
    }
    
    @IBAction func actionFlowButtonTapped(_ sender: UIButton) {
        NotificationCenter.default.post(name: .actionFlowButtonTapped, object: nil)
    }
    
    override func viewWillTransition(to size: CGSize, with coordinator: UIViewControllerTransitionCoordinator) {
        super.viewWillTransition(to: size, with: coordinator)
        resizeConstant()
        actionBoard.moveView()
    }
    
    private func resizeConstant() {
        stackViewTrailing.constant = UIScreen.main.isPortrait ? 48 : 330
    }
    
    private func editCardButtonTapped() {
        guard let popupViewController = storyboard?.instantiateViewController(withIdentifier: NameSpace.identifier.popupViewController) as? EditCardViewController else { return }
        
        popupViewController.modalPresentationStyle = .overFullScreen
        
        self.present(popupViewController, animated: false)
    }
}

extension MyTaskViewController: UIPopoverPresentationControllerDelegate {
    func adaptivePresentationStyle(for controller: UIPresentationController) -> UIModalPresentationStyle {
        return .none
    }
}
