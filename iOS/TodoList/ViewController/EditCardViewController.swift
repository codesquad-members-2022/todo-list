import UIKit

class EditCardViewController: UIViewController {
    @IBOutlet private weak var centerView: UIView!
    var targetTitle: String?
    
    private var editCardView: EditCardView = Bundle.main.loadNibNamed(nibTitle.editCardView, owner: nil, options: nil)?.first as? EditCardView ?? EditCardView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpView()
    }
    
    private func setUpView() {
        self.editCardView.delegate = self
        self.centerView.addSubview(editCardView)
    }
}

extension EditCardViewController: EditCardViewDelegate {
    func didCancelButtonTouched() {
        self.dismiss(animated: false)
    }

    func didAddButtonTouched(completion: (String) -> RequestCardData) {
        guard let title = self.targetTitle else { return }
        URLManager<TaskCard>.request(api: .post(completion(title)), completionHandler: {_ in NotificationCenter.default.post(name: .getTaskBoardData, object: nil)})
        self.dismiss(animated: false)
    }
}
