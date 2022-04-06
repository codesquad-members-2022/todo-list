import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var taskListStackView: UIStackView!
    
    @IBOutlet weak var actionFlowButton: UIButton!
    @IBOutlet weak var stackViewTrailing: NSLayoutConstraint!
    
    private let todoViewController = TaskListBoardViewController()
    private let doingViewController = TaskListBoardViewController()
    private let doneViewController = TaskListBoardViewController()
    override func viewDidLoad() {
        super.viewDidLoad()
        addChild(todoViewController)
        addChild(doingViewController)
        addChild(doneViewController)
        
        layout()
    }
    
    @IBAction func actionFlowButtonTapped(_ sender: UIButton) {
        editCardButtonTapped()
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
        guard let popVC = storyboard?.instantiateViewController(withIdentifier: "popVC") as? EditCardViewController else { return }
        
        popVC.modalPresentationStyle = .overFullScreen
        
        self.present(popVC, animated: false)
    }
}

extension ViewController: UIPopoverPresentationControllerDelegate {
    func adaptivePresentationStyle(for controller: UIPresentationController) -> UIModalPresentationStyle {
               return .none
           }
}

    
