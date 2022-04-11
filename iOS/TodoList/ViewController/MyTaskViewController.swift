import UIKit

class MyTaskViewController: UIViewController {
    @IBOutlet private weak var taskListStackView: TaskBoardView!
    @IBOutlet private weak var actionBoard: ActionBoardView!
    
    @IBOutlet private weak var actionFlowButton: UIButton!
    @IBOutlet private weak var stackViewTrailing: NSLayoutConstraint!
    
    private let todoViewController = TaskCardListViewController()
    private let doingViewController = TaskCardListViewController()
    private let doneViewController = TaskCardListViewController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        resizeConstant()
        actionBoard.moveView()
        setupChildViewContoller()
    }
    
    @IBAction func actionFlowButtonTapped(_ sender: UIButton) {
        NotificationCenter.default.post(name: Notification.Name.actionFlowButtonTapped, object: nil)
    }
    
    private func setupChildViewContoller() {
        self.taskListStackView.addArrangedSubview(todoViewController.view)
        self.taskListStackView.addArrangedSubview(doingViewController.view)
        self.taskListStackView.addArrangedSubview(doneViewController.view)
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
