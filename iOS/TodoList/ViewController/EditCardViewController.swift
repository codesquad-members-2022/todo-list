import UIKit

class EditCardViewController: UIViewController {
    @IBOutlet private weak var centerView: UIView!
    var targetTitle: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpView()
        observe()
    }
    
    private func setUpView() {
        guard let editCardView = Bundle.main.loadNibNamed(nibTitle.editCardView, owner: nil, options: nil)?.first as? EditCardView else { return }
        self.centerView.addSubview(editCardView)
    }
    
    private func observe() {
        NotificationCenter.default.addObserver(forName: .cancelButtonTapped, object: nil, queue: .main, using: {_ in
            self.dismiss(animated: false)
        })
        NotificationCenter.default.addObserver(forName: .editButtonTapped, object: nil, queue: .main, using: { noti in
            guard let cardData = noti.userInfo?[NotificationKeyValue.targetData] as? (String) -> RequestCardData else { return }
            guard let targetTitle = self.targetTitle else { return }
            URLManager<TaskCard>.request(api: .post(cardData(targetTitle)), completionHandler: {_ in NotificationCenter.default.post(name: .getTaskBoardData, object: nil)})
            
            self.dismiss(animated: false)
        })
    }
}
