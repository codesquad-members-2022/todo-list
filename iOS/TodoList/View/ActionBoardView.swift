import UIKit

class ActionBoardView: UIView {
    private var isInScreen: Bool = false
    private let width : CGFloat = 428
    
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
        let blank = isInScreen ? (UIScreen.main.bounds.width - self.width) : UIScreen.main.bounds.width
        self.frame = CGRect(x: blank, y: 0, width: self.width, height: UIScreen.main.bounds.height)
    }
    
    private func animation(with handler: @escaping ()->Void) {
        UIView.animate(withDuration: 0.2, animations: handler)
    }
    
    private func addObserver() {
        NotificationCenter.default.addObserver(forName: .actionFlowButtonTapped, object: nil, queue: .main, using: {_ in
            self.toggle()
        })
        NotificationCenter.default.addObserver(forName: .actionFlowCloseButtonTapped, object: nil, queue: .main, using: {_ in
            self.toggle()
        })
    }

}
