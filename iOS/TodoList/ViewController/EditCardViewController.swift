import UIKit

class EditCardViewController: UIViewController {
    @IBOutlet private weak var centerView: UIView!
    var targetTitle: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        guard let editCardView = Bundle.main.loadNibNamed(NameSpace.nib.editCardView, owner: nil, options: nil)?.first as? EditCardView else { return }
        self.centerView.addSubview(editCardView)
        
        NotificationCenter.default.addObserver(forName: .cancelButtonTapped, object: nil, queue: .main, using: {_ in
            self.dismiss(animated: false)
        })
        NotificationCenter.default.addObserver(forName: .editButtonTapped, object: nil, queue: .main, using: { noti in
            guard let cardData = noti.userInfo?[NotificationKeyValue.targetData] as? (String) -> RequestCardData else { return }
            let card = cardData(self.targetTitle ?? "")
            URLManager.post(with: card)

            self.dismiss(animated: false)
        })
    }
}
