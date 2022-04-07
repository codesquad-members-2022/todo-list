import UIKit

class MyTaskViewController: UIViewController {
    
    @IBOutlet weak var taskListStackView: UIStackView!
    
    @IBOutlet weak var actionFlowButton: UIButton!
    @IBOutlet weak var stackViewTrailing: NSLayoutConstraint!
    
    private let todoViewController = TaskListBoardViewController()
    private let doingViewController = TaskListBoardViewController()
    private let doneViewController = TaskListBoardViewController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupChildViewContoller()
    }
    
    @IBAction func actionFlowButtonTapped(_ sender: UIButton) {
        editCardButtonTapped()
    }
    
    private func setupChildViewContoller() {
        self.addChild(todoViewController)
        self.addChild(doingViewController)
        self.addChild(doneViewController)
        
        self.children.forEach{
            self.taskListStackView.addArrangedSubview($0.view)
        }
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

