import UIKit

class ActionBoardView: UIView {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addObserver()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        addObserver()
    }
    
    @IBAction func menuButtonTapped() {
        self.animation(moveTo: 766)
    }
    
    @IBAction func closeButtonTapped() {
        self.animation(moveTo: 1194)
    }
    
    private func animation(moveTo xCoordinate: Int) {
        UIView.animate(withDuration: 0.2, animations: {
            self.frame = CGRect(x: xCoordinate, y: 0, width: 428, height: 834)
        })
    }
    
    private func addObserver() {
        NotificationCenter.default.addObserver(self, selector: #selector(menuButtonTapped), name: Notification.Name.actionFlowButtonTapped, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(closeButtonTapped), name: Notification.Name.actionFlowCloseButtonTapped, object: nil)
    }

}
