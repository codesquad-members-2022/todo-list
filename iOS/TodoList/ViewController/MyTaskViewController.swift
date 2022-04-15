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
        loadTaskBoard()
        resizeConstant()
        actionBoard.moveView()
    }
    
    private func observe() {
        NotificationCenter.default.addObserver(forName: .getTaskBoardData, object: nil, queue: .main, using: {_ in self.loadTaskBoard()})
        NotificationCenter.default.addObserver(forName: .addTaskButtonTapped, object: nil, queue: .main, using: self.addCardButtonTapped(noti:))
        NotificationCenter.default.addObserver(forName: .editMenuTapped, object: nil, queue: .main, using: self.editCardButtonTapped(noti:))
    }
    
    private func loadTaskBoard() {
            URLManager<TaskBoard>.request(api: .get, completionHandler: self.setChild(with:))
    }
    
    private func setChild(with taskBoard: TaskBoard) {
        DispatchQueue.main.async {
            self.taskBoardView.subviews.forEach { $0.removeFromSuperview() }
            ["해야할 일", "하고 있는 일", "완료된 일"].forEach{
                self.setTaskList(key: $0, value: taskBoard.cards[$0] ?? [])
            }
        }
    }
    
    private func setTaskList(key: String, value: [TaskCard]) {
        let controller = TaskCardListViewController()
        self.addChild(controller)
        self.taskBoardView.addArrangedSubview(controller.view)
        controller.set(key, with: value)
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
    
    private func addCardButtonTapped(noti: Notification) {
        guard let popupViewController = storyboard?.instantiateViewController(withIdentifier: identifier.popupViewController) as? EditCardViewController else { return }
        popupViewController.modalPresentationStyle = .overFullScreen
        guard let title = noti.userInfo?[NotificationKeyValue.targetViewTitle] as? String else { return }
        popupViewController.section = title
        self.present(popupViewController, animated: false)
    }
    
    private func editCardButtonTapped(noti: Notification) {
        guard let popupViewController = storyboard?.instantiateViewController(withIdentifier: identifier.popupViewController) as? EditCardViewController else { return }
        popupViewController.modalPresentationStyle = .overFullScreen
        guard let card = noti.userInfo?[NotificationKeyValue.editTaskData] as? TaskCard else { return }
        popupViewController.card = card
        popupViewController.section = card.section
        self.present(popupViewController, animated: false)
    }
    
}

extension MyTaskViewController: UIPopoverPresentationControllerDelegate {
    func adaptivePresentationStyle(for controller: UIPresentationController) -> UIModalPresentationStyle {
        return .none
    }
}
