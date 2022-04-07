import UIKit

class HeaderView: UIView {
    
    private var titleLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont(name: FontFactory.normal, size: 50)
        label.text = "TO-DO LIST"
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    
    weak var delegate: HeaderViewDelegate?
    
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
        addSubview(titleLabel)
    }
    
    private func setConstraints() {
        titleLabel.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        titleLabel.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 48).isActive = true
        titleLabel.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.25).isActive = true
        titleLabel.heightAnchor.constraint(equalTo: heightAnchor, multiplier: 0.7).isActive = true
        
    }
}
