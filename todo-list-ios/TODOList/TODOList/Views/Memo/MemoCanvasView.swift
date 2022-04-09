import Foundation
import UIKit

class MemoCanvasView: UIView {
    
    private let memoContainerStackView: UIStackView = {
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
    
    let progressContainerView: MemoContainerView = {
        let view = MemoContainerView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    let doneContainerView: MemoContainerView = {
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
        addSubview(memoContainerStackView)
        memoContainerStackView.addArrangedSubview(todoContainerView)
        memoContainerStackView.addArrangedSubview(progressContainerView)
        memoContainerStackView.addArrangedSubview(doneContainerView)
      
        memoContainerStackView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 48).isActive = true
        memoContainerStackView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        memoContainerStackView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -48).isActive = true
        memoContainerStackView.heightAnchor.constraint(equalTo: heightAnchor).isActive = true
        
        todoContainerView.topAnchor.constraint(equalTo: memoContainerStackView.topAnchor).isActive = true
        todoContainerView.bottomAnchor.constraint(equalTo: memoContainerStackView.bottomAnchor).isActive = true
        progressContainerView.topAnchor.constraint(equalTo: memoContainerStackView.topAnchor).isActive = true
        progressContainerView.bottomAnchor.constraint(equalTo: memoContainerStackView.bottomAnchor).isActive = true
        doneContainerView.topAnchor.constraint(equalTo: memoContainerStackView.topAnchor).isActive = true
        doneContainerView.bottomAnchor.constraint(equalTo: memoContainerStackView.bottomAnchor).isActive = true
    }
}
