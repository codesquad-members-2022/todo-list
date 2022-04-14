import UIKit

class MyTaskViewController: UIViewController {
    @IBOutlet private weak var taskBoardView: TaskBoardView!
    @IBOutlet private weak var actionBoard: ActionBoardView!
    
    @IBOutlet private weak var actionFlowButton: UIButton!
    @IBOutlet private weak var stackViewTrailing: NSLayoutConstraint!
    
    private var taskBoard: [TaskCardList] = []

    override func viewDidLoad() {
        super.viewDidLoad()
        observe()
        DispatchQueue.main.async {
            URLManager.getTasks()
        }
        resizeConstant()
        actionBoard.moveView()
    }
    
    private func observe() {
        NotificationCenter.default.addObserver(forName: .getTaskBoardData, object: nil, queue: .main, using: setChild(with:))
        NotificationCenter.default.addObserver(forName: .addTaskButtonTapped, object: nil, queue: .main, using: self.editCardButtonTapped(noti:))
    }
    
    func setChild(with notification: Notification) {
        guard let taskBoard = notification.userInfo?[NotificationKeyValue.getTaskData] as? [String:[TaskCard]] else { return }
        self.taskBoardView.subviews.forEach { $0.removeFromSuperview() }
        for (key, value) in taskBoard {
            let controller = TaskCardListViewController()
            self.addChild(controller)
            self.taskBoardView.addArrangedSubview(controller.view)
            controller.set(key, with: value)
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
    
    private func editCardButtonTapped(noti: Notification) {
        guard let popupViewController = storyboard?.instantiateViewController(withIdentifier: identifier.popupViewController) as? EditCardViewController else { return }
        popupViewController.modalPresentationStyle = .overFullScreen
        guard let title = noti.userInfo?[NotificationKeyValue.targetViewTitle] as? String else { return }
        popupViewController.targetTitle = title
        self.present(popupViewController, animated: false)
    }
    
}

extension MyTaskViewController: UIPopoverPresentationControllerDelegate {
    func adaptivePresentationStyle(for controller: UIPresentationController) -> UIModalPresentationStyle {
        return .none
    }
}
