import UIKit

class HeaderView: UIView {
    
    private var titleLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont(name: FontFactory.normal, size: 40)
        label.text = "TO-DO LIST"
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private lazy var sideMenuButton: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setBackgroundImage(UIImage(named: "menu"), for: .normal)
        button.addAction(UIAction(handler: { _ in
            self.delegate?.headerViewButtonDidTap()
        }), for: .touchUpInside)
        return button
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
        addSubview(sideMenuButton)
    }
    
    private func setConstraints() {
        titleLabel.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        titleLabel.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 48).isActive = true

        sideMenuButton.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        sideMenuButton.widthAnchor.constraint(equalToConstant: 20).isActive = true
        sideMenuButton.heightAnchor.constraint(equalToConstant: 20).isActive = true
        sideMenuButton.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -50).isActive = true
    }
}
