import Foundation
import UIKit

class MemoContainerView: UIView {
    
    let horizontalStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .horizontal
        stackView.spacing = 5
        stackView.alignment = .fill
        stackView.distribution = .fillEqually
        return stackView
    }()
    
    let categoryLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: FontFactory.bold, size: 20)
        label.textColor = UIColor(named: ColorAsset.black)
        label.text = "해야 할 일"
        label.textAlignment = .left
        return label
    }()
    
    let countView: UIView = {
        let label = UIView()
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    let countLabel: UILabel = {
        let label = UILabel()
        label.text = "3"
        label.font = UIFont(name: FontFactory.bold, size: 14)
        label.textColor = UIColor(named: ColorAsset.black)
        label.backgroundColor = UIColor(named: ColorAsset.gray4)
        label.layer.masksToBounds = true
        label.layer.cornerRadius = 15
        label.textAlignment = .center
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    let buttonView: UIView = {
        let label = UIView()
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    let button: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setBackgroundImage(UIImage(named: "add"), for: .normal)
        return button
    }()
    
    let tableView: UITableView = {
        let tableView = UITableView(frame: .zero, style: .grouped)
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
        buttonView.addSubview(button)
        horizontalStackView.addArrangedSubview(categoryLabel)
        horizontalStackView.addArrangedSubview(countView)
        horizontalStackView.addArrangedSubview(buttonView)
        horizontalStackView.setCustomSpacing(110, after: countView)
        
        addSubview(horizontalStackView)
        addSubview(tableView)
    }
    
    private func setConstraints() {
        
        button.centerYAnchor.constraint(equalTo: buttonView.centerYAnchor).isActive = true
        button.trailingAnchor.constraint(equalTo: buttonView.trailingAnchor, constant: -20).isActive = true
        
        countLabel.widthAnchor.constraint(equalToConstant: 30).isActive = true
        countLabel.heightAnchor.constraint(equalToConstant: 30).isActive = true
        countLabel.leadingAnchor.constraint(equalTo: countView.leadingAnchor).isActive = true
        countLabel.centerYAnchor.constraint(equalTo: countView.centerYAnchor).isActive = true
        
        horizontalStackView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        horizontalStackView.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        horizontalStackView.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
        horizontalStackView.heightAnchor.constraint(equalToConstant: 50).isActive = true
        
        tableView.topAnchor.constraint(equalTo: horizontalStackView.bottomAnchor).isActive = true
        tableView.leadingAnchor.constraint(equalTo: horizontalStackView.leadingAnchor).isActive = true
        tableView.trailingAnchor.constraint(equalTo: horizontalStackView.trailingAnchor).isActive = true
        tableView.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true
    }
}
