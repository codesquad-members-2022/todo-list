import UIKit

class TaskListStackView: UIStackView {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setup()
    }
    
    required init(coder: NSCoder) {
        super.init(coder: coder)
        setup()
    }
    
    private func setup() {
        self.translatesAutoresizingMaskIntoConstraints = false
        self.axis = .horizontal
        self.alignment = .fill
        self.distribution = .fillEqually
        self.spacing = 22
    }

}
