import UIKit

class EditCardViewController: UIViewController {
    @IBOutlet weak var centerView: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        guard let editCardView = Bundle.main.loadNibNamed(NameSpace.nib.editCardView, owner: nil, options: nil)?.first as? EditCardView else { return }
        self.centerView.addSubview(editCardView)
    }
}
