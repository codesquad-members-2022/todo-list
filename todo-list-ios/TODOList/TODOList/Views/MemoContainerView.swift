import Foundation
import UIKit

class MemoContainerView: UIView {
    
    let horizontalStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .horizontal
        stackView.spacing = 10
        stackView.alignment = .fill
        stackView.distribution = .fillEqually
        return stackView
    }()
    
    let categoryLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: FontFactory.normal, size: 18)
        label.textColor = UIColor(named: ColorAsset.black)
        label.text = "해야 할 일"
        label.textAlignment = .center
        return label
    }()
    
    let countLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: FontFactory.bold, size: 14)
        label.textColor = UIColor(named: ColorAsset.black)
        label.backgroundColor = UIColor(named: ColorAsset.gray4)
        label.text = "3"
        label.textAlignment = .center
        return label
    }()
    
    let button: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setBackgroundImage(UIImage(named: "add"), for: .normal)
        return button
    }()
    
    let tableView: UITableView = {
        let tableView = UITableView()
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.register(MemoTableViewCell.self, forCellReuseIdentifier: MemoTableViewCell.identifier)
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
        horizontalStackView.addArrangedSubview(categoryLabel)
        horizontalStackView.addArrangedSubview(countLabel)
        horizontalStackView.addArrangedSubview(button)
        addSubview(horizontalStackView)
        addSubview(tableView)
    }
    
    private func setConstraints() {
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
