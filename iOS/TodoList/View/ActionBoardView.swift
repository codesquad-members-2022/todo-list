import UIKit

class ActionBoardView: UIView {
    private var isInScreen: Bool = false
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addObserver()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        addObserver()
    }
    
    @IBAction private func toggle() {
        isInScreen.toggle()
        animation(with: moveView)
    }
    
    func moveView() {
        let width : CGFloat = 428
        let blank = isInScreen ? (UIScreen.main.bounds.width - width) : UIScreen.main.bounds.width
        self.frame = CGRect(x: blank, y: 0, width: width, height: UIScreen.main.bounds.height)
    }
    
    private func animation(with handler: @escaping ()->Void) {
        UIView.animate(withDuration: 0.2, animations: handler)
    }
    
    private func addObserver() {
        NotificationCenter.default.addObserver(self, selector: #selector(toggle), name: Notification.Name.actionFlowButtonTapped, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(toggle), name: Notification.Name.actionFlowCloseButtonTapped, object: nil)
    }

}
