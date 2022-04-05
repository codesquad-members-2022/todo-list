import Foundation
import UIKit

class SideView: UIView {
    
    let closeButton: UIButton = {
        let button = UIButton(type: .close)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.backgroundColor = .white
        return button
    }()
    
    let tableView: UITableView = {
        let view = UITableView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.register(SideViewTableViewCell.self, forCellReuseIdentifier: SideViewTableViewCell.identifier)
        return view
    }()
    
    required override init(frame: CGRect) {
        super.init(frame: frame)
        setLayout()
    }
    
    override required init?(coder: NSCoder) {
        super.init(coder: coder)
        setLayout()
    }
    
    private func setLayout() {
        self.addSubview(closeButton)
        self.addSubview(tableView)
        NSLayoutConstraint.activate([
            closeButton.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor),
            closeButton.widthAnchor.constraint(equalToConstant: 60),
            closeButton.heightAnchor.constraint(equalToConstant: 60),
            closeButton.trailingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.trailingAnchor, constant: 0),
            
            tableView.topAnchor.constraint(equalTo: self.closeButton.bottomAnchor, constant: 20),
            tableView.bottomAnchor.constraint(equalTo: self.safeAreaLayoutGuide.bottomAnchor, constant: 0),
            tableView.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor, constant: 0),
            tableView.trailingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.trailingAnchor, constant: 0)
        ])
    }
}
