import UIKit

class MemoCanvasView: UIView {
    
    let memoContainerStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .horizontal
        stackView.spacing = 35
        stackView.alignment = .top
        stackView.distribution = .fillEqually
        return stackView
    }()

}
