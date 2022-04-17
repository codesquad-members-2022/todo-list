import UIKit

class PopupViewController: UIViewController {

    weak var delegate: PopupViewDelegate?
    private var alertTitle: String = ""
    private var containerType: MemoStatus?
    var memoTitle: String = ""
    var memoContent: String = ""
    
    lazy var popupCardView: PopupCardView = {
        let view = PopupCardView()
        view.alertLabel.text = alertTitle
        view.titleField.text = memoTitle
        view.contentField.text = memoContent
        view.memoContainerType = containerType
        view.layer.cornerRadius = 10
        view.backgroundColor = .white
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    convenience init(containerType: MemoStatus) {
        self.init()
        
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
    
    // TODO: Server에 데이터 POST
    func popupCardOkButtonDidTap(title: String, content: String, status: MemoStatus) {
        let memo = Memo(title: title, content: content, name: "sampleI", status: status)
        self.delegate?.popupViewAddButtonDidTap(memo: memo)
        
        self.dismiss(animated: true)
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
