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
    
    private (set) var containerViews: [Identifier: MemoContainerView] = [:]
    
    required override init(frame: CGRect) {
        super.init(frame: frame)
        
        addViews()
        setConstraints()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        
        addViews()
        setConstraints()
    }
    
    private func addViews() {
        addSubview(memoContainerStackView)
        for identifier in Identifier.allCases {
            let containerView = MemoContainerView()
            containerView.translatesAutoresizingMaskIntoConstraints = false
            containerViews[identifier] = containerView
            memoContainerStackView.addArrangedSubview(containerView)
        }
    }
    
    private func setConstraints() {

        memoContainerStackView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 48).isActive = true
        memoContainerStackView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        memoContainerStackView.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.75).isActive = true
        memoContainerStackView.heightAnchor.constraint(equalTo: heightAnchor).isActive = true
        
        for ( _ , containerView) in containerViews {
            containerView.topAnchor.constraint(equalTo: memoContainerStackView.topAnchor).isActive = true
            containerView.bottomAnchor.constraint(equalTo: memoContainerStackView.bottomAnchor).isActive = true
        }
    }
}
