import UIKit

class PopupViewController: UIViewController {

    private var popupCardView: PopupCardView = {
        let view = PopupCardView()
        view.layer.cornerRadius = 10
        view.backgroundColor = .white
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        addViews()
    }
    
    
    private func addViews() {
        view.addSubview(popupCardView)
        popupCardView.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        popupCardView.centerYAnchor.constraint(equalTo: view.centerYAnchor).isActive = true
    }
}
