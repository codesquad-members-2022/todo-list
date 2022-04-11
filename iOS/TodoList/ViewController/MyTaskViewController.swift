import UIKit

class MyTaskViewController: UIViewController {
    @IBOutlet private weak var taskListStackView: TaskBoardView!
    @IBOutlet private weak var actionBoard: UIView!
    
    @IBOutlet private weak var actionFlowButton: UIButton!
    @IBOutlet private weak var stackViewTrailing: NSLayoutConstraint!
    
    private let todoViewController = TaskCardListViewController()
    private let doingViewController = TaskCardListViewController()
    private let doneViewController = TaskCardListViewController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupChildViewContoller()
    }
    
    @IBAction func actionFlowButtonTapped(_ sender: UIButton) {
        editCardButtonTapped()
    }
    
    private func setupChildViewContoller() {
        self.taskListStackView.addArrangedSubview(todoViewController.view)
        self.taskListStackView.addArrangedSubview(doingViewController.view)
        self.taskListStackView.addArrangedSubview(doneViewController.view)
    }
    
    override func viewWillTransition(to size: CGSize, with coordinator: UIViewControllerTransitionCoordinator) {
        super.viewWillTransition(to: size, with: coordinator)
        resizeConstant()
    }
    
    private func resizeConstant() {
        if UIDevice.current.orientation.isPortrait {
            stackViewTrailing.constant = 48
        }else {
            stackViewTrailing.constant = 330
        }
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
