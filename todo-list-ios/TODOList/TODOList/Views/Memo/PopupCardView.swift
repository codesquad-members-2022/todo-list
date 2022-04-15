import UIKit

class PopupCardView: UIView {

    weak var delegate: PopupCardViewDelegate?
    var memoContainerType: MemoContainerType?
    
    private let containerView: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.backgroundColor = .white
        return view
    }()
    
    let alertLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: FontFactory.bold, size: 16)
        return label
    }()
    
    let titleField: UITextField = {
        let textField = UITextField()
        textField.translatesAutoresizingMaskIntoConstraints = false
        textField.placeholder = "제목을 입력하세요"
        textField.font = UIFont(name: FontFactory.bold, size: 14)
        textField.textColor = UIColor(named: ColorAsset.gray3)
        textField.becomeFirstResponder()
        return textField
    }()
    
    let contentField: UITextField = {
        let textField = UITextField()
        textField.translatesAutoresizingMaskIntoConstraints = false
        textField.placeholder = "내용을 입력하세요"
        textField.font = UIFont(name: FontFactory.normal, size: 14)
        textField.textColor = UIColor(named: ColorAsset.gray3)
        return textField
    }()
    
    private let verticalStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .vertical
        stackView.alignment = .fill
        stackView.distribution = .fillEqually
        stackView.spacing = 2
        return stackView
    }()
    
    lazy var cancelButton: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("취소", for: .normal)
        button.backgroundColor = UIColor(named: ColorAsset.gray5)
        button.setTitleColor(UIColor(named: ColorAsset.gray3), for: .normal)
        button.titleLabel?.font = UIFont(name: FontFactory.normal, size: 14)
        button.layer.cornerRadius = 6
        button.addAction(UIAction(handler: { _ in
            self.delegate?.popupCardCancelButtonDidTap()
        }), for: .touchUpInside)
        return button
    }()
    
    lazy var okButton: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("등록", for: .normal)
        button.backgroundColor = UIColor(named: ColorAsset.blue)
        button.setTitleColor(UIColor(named: ColorAsset.white), for: .normal)
        button.titleLabel?.font = UIFont(name: FontFactory.normal, size: 14)
        button.layer.cornerRadius = 6
        button.addAction(UIAction(handler: { _ in
            self.delegate?.popupCardOkButtonDidTap(title: self.titleField.text!, content: self.contentField.text!, status: self.memoContainerType!)
        }), for: .touchUpInside)
        return button
    }()
    
    private let horizontalStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .horizontal
        stackView.alignment = .fill
        stackView.distribution = .fillEqually
        stackView.spacing = 10
        return stackView
    }()
    
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
        containerView.addSubview(alertLabel)
        
        verticalStackView.addArrangedSubview(titleField)
        verticalStackView.addArrangedSubview(contentField)
        containerView.addSubview(verticalStackView)
        
        horizontalStackView.addArrangedSubview(cancelButton)
        horizontalStackView.addArrangedSubview(okButton)
        containerView.addSubview(horizontalStackView)
        
        addSubview(containerView)
    }
    
    private func setConstraints() {
        containerView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 16).isActive = true
        containerView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -16).isActive = true
        containerView.topAnchor.constraint(equalTo: topAnchor, constant: 16).isActive = true
        containerView.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -16).isActive = true
        containerView.widthAnchor.constraint(equalToConstant: 400).isActive = true
        containerView.heightAnchor.constraint(equalToConstant: 160).isActive = true
        
        alertLabel.leadingAnchor.constraint(equalTo: containerView.leadingAnchor).isActive = true
        alertLabel.trailingAnchor.constraint(equalTo: containerView.trailingAnchor).isActive = true
        alertLabel.topAnchor.constraint(equalTo: containerView.topAnchor).isActive = true
        alertLabel.heightAnchor.constraint(equalToConstant: 20).isActive = true
        
        verticalStackView.leadingAnchor.constraint(equalTo: alertLabel.leadingAnchor).isActive = true
        verticalStackView.trailingAnchor.constraint(equalTo: alertLabel.trailingAnchor).isActive = true
        verticalStackView.topAnchor.constraint(equalTo: alertLabel.bottomAnchor, constant: 16).isActive = true
        verticalStackView.heightAnchor.constraint(equalToConstant: 60).isActive = true
        
        horizontalStackView.trailingAnchor.constraint(equalTo: alertLabel.trailingAnchor).isActive = true
        horizontalStackView.topAnchor.constraint(equalTo: verticalStackView.bottomAnchor, constant: 16).isActive = true
        horizontalStackView.bottomAnchor.constraint(equalTo: containerView.bottomAnchor).isActive = true
        horizontalStackView.widthAnchor.constraint(equalToConstant: 220).isActive = true
    }
}
