import Foundation
import UIKit

class MemoCanvasView: UIView {
    
    private let stackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .horizontal
        stackView.spacing = 10
        stackView.alignment = .top
        return stackView
    }()
    
    private let todoContainerView: MemoContainerView = {
        let view = MemoContainerView()
        view.backgroundColor = .blue
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    private let progressContainerView: MemoContainerView = {
        let view = MemoContainerView()
        view.backgroundColor = .green
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    private let doneContainerView: MemoContainerView = {
        let view = MemoContainerView()
        view.backgroundColor = .green
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    required override init(frame: CGRect) {
        super.init(frame: frame)
        
        addViews()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        
        addViews()
    }
    
    private func addViews() {
        stackView.addArrangedSubview(todoContainerView)
        stackView.addArrangedSubview(progressContainerView)
        stackView.addArrangedSubview(doneContainerView)
        addSubview(stackView)
        
        stackView.widthAnchor.constraint(equalToConstant: 300).isActive = true
        stackView.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        stackView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        stackView.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
        stackView.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true
    }
}
