import UIKit

class MemoContainerView: UIView {
    
    weak var delegate: MemoContainerViewDelegate?
    
    var containerType: MemoStatus?

    private let containerTitleView: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    let categoryLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: FontFactory.bold, size: 20)
        label.textColor = UIColor(named: ColorAsset.black)
        label.textAlignment = .left
        return label
    }()
    
    private let countView: UIView = {
        let label = UIView()
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    let countLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont(name: FontFactory.bold, size: 14)
        label.textColor = UIColor(named: ColorAsset.black)
        label.backgroundColor = UIColor(named: ColorAsset.gray4)
        label.layer.masksToBounds = true
        label.layer.cornerRadius = 15
        label.textAlignment = .center
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private lazy var addButton: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setBackgroundImage(UIImage(named: "add"), for: .normal)
        button.addAction(UIAction(handler: { _ in
            self.delegate?.addButtonDidTap(containerType: self.containerType!)
        }), for: .touchUpInside)
        return button
    }()
    
    let tableView: UITableView = {
        let tableView = UITableView(frame: .zero, style: .grouped)
        tableView.sectionHeaderHeight = 0
        tableView.tableHeaderView = UIView(frame: CGRect(origin: .zero, size: CGSize(width: 0, height: CGFloat.leastNormalMagnitude)))
        tableView.separatorStyle = .none
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.register(MemoTableViewCell.self, forCellReuseIdentifier: MemoTableViewCell.identifier)
        tableView.backgroundColor = .clear
        return tableView
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
        
        countView.addSubview(countLabel)
        containerTitleView.addSubview(categoryLabel)
        containerTitleView.addSubview(countView)
        containerTitleView.addSubview(addButton)
        
        addSubview(containerTitleView)
        addSubview(tableView)
    }
    
    private func setConstraints() {
        
        categoryLabel.leadingAnchor.constraint(equalTo: containerTitleView.leadingAnchor).isActive = true
        categoryLabel.centerYAnchor.constraint(equalTo: containerTitleView.centerYAnchor).isActive = true
        
        countLabel.widthAnchor.constraint(equalTo: countView.widthAnchor).isActive = true
        countLabel.heightAnchor.constraint(equalTo: countView.heightAnchor).isActive = true
        countLabel.centerXAnchor.constraint(equalTo: countView.centerXAnchor).isActive = true
        countLabel.centerYAnchor.constraint(equalTo: countView.centerYAnchor).isActive = true
        
        countView.leadingAnchor.constraint(equalTo: categoryLabel.trailingAnchor, constant: 10).isActive = true
        countView.centerYAnchor.constraint(equalTo: containerTitleView.centerYAnchor).isActive = true
        countView.widthAnchor.constraint(equalToConstant: 30).isActive = true
        countView.heightAnchor.constraint(equalTo: countView.widthAnchor).isActive = true
        
        addButton.trailingAnchor.constraint(equalTo: containerTitleView.trailingAnchor).isActive = true
        addButton.centerYAnchor.constraint(equalTo: containerTitleView.centerYAnchor).isActive = true
        
        containerTitleView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        containerTitleView.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        containerTitleView.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
        containerTitleView.heightAnchor.constraint(equalToConstant: 50).isActive = true
        
        tableView.topAnchor.constraint(equalTo: containerTitleView.bottomAnchor).isActive = true
        tableView.leadingAnchor.constraint(equalTo: containerTitleView.leadingAnchor).isActive = true
        tableView.trailingAnchor.constraint(equalTo: containerTitleView.trailingAnchor).isActive = true
        tableView.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true
    }
}
