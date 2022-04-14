import UIKit

class PopupViewController: UIViewController {

    weak var delegate: PopupViewDelegate?
    private var alertTitle: String = ""
    private var containerType: MemoContainerType?
    
    lazy var popupCardView: PopupCardView = {
        let view = PopupCardView()
        view.alertLabel.text = alertTitle
        view.layer.cornerRadius = 10
        view.backgroundColor = .white
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    convenience init(containerType: MemoContainerType) {
        self.init()
        print("1235")
        self.containerType = containerType
        self.alertTitle = "\(containerType) 추가하기"
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        addViews()
        setTapGesture()
        
        self.popupCardView.delegate = self
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


extension PopupViewController: PopupCardViewDelegate {
    func popupCardCancelButtonDidTap() {
        dismiss(animated: true)
    }
    
    func popupCardOkButtonDidTap() {
        // TODO: Server에 데이터 POST
    }
    
    func popupCardOkButtonDidTapTemp(title: String, content: String) {
        let memo = Memo(title: title, content: content, name: "JK")
        
        self.delegate?.addButtonDidTap(memo: memo)
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
