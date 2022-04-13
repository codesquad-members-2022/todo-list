import UIKit

class PopupViewController: UIViewController {

    var alertTitle: String = ""
    
    lazy var popupCardView: PopupCardView = {
        let view = PopupCardView()
        view.alertLabel.text = alertTitle
        view.layer.cornerRadius = 10
        view.backgroundColor = .white
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        addViews()
        setTapGesture()
    }
    
    
    private func addViews() {
        view.addSubview(popupCardView)
        view.backgroundColor = .black.withAlphaComponent(0.4)
        popupCardView.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        popupCardView.centerYAnchor.constraint(equalTo: view.centerYAnchor).isActive = true
    }
    
    private func setTapGesture() {
        let tapGesture = UITapGestureRecognizer()
        tapGesture.delegate = self
        view.addGestureRecognizer(tapGesture)
    }
}


extension PopupViewController: UIGestureRecognizerDelegate {
    func gestureRecognizer(_ gestureRecognizer: UIGestureRecognizer, shouldReceive touch: UITouch) -> Bool {
        let touchedPoint = touch.location(in: view)
        
        if !isPointInPopUpViewRange(position: touchedPoint) {
            dismiss(animated: true)
        }
        return true
    }
    
    private func isPointInPopUpViewRange(position: CGPoint) -> Bool {
        if (position.x >= popupCardView.frame.minX && position.x <= popupCardView.frame.maxX) && (position.y >= popupCardView.frame.minY && position.y <= popupCardView.frame.maxY) {
            return true
        }
        return false
    }
}
