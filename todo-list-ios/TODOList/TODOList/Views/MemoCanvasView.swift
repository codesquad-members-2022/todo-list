import Foundation
import UIKit

class MemoCanvasView: UIView {
    
    private let stackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .horizontal
        stackView.spacing = 10
        stackView.alignment = .top
        stackView.distribution = .fillEqually
        return stackView
    }()
    
    let todoContainerView: MemoContainerView = {
        let view = MemoContainerView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    private let progressContainerView: MemoContainerView = {
        let view = MemoContainerView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    private let doneContainerView: MemoContainerView = {
        let view = MemoContainerView()
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
        addSubview(stackView)
        stackView.addArrangedSubview(todoContainerView)
        stackView.addArrangedSubview(progressContainerView)
        stackView.addArrangedSubview(doneContainerView)
      
        stackView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 48).isActive = true
        stackView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        stackView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -48).isActive = true
        stackView.heightAnchor.constraint(equalTo: heightAnchor).isActive = true
        
        todoContainerView.topAnchor.constraint(equalTo: stackView.topAnchor).isActive = true
        todoContainerView.bottomAnchor.constraint(equalTo: stackView.bottomAnchor).isActive = true
        progressContainerView.topAnchor.constraint(equalTo: stackView.topAnchor).isActive = true
        progressContainerView.bottomAnchor.constraint(equalTo: stackView.bottomAnchor).isActive = true
        doneContainerView.topAnchor.constraint(equalTo: stackView.topAnchor).isActive = true
        doneContainerView.bottomAnchor.constraint(equalTo: stackView.bottomAnchor).isActive = true
    }
}
