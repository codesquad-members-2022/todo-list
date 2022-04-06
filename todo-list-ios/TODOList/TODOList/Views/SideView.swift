import Foundation
import UIKit

class SideView: UIView {
    
    weak var delegate: SideViewDelegate?
    
    let closeButton: UIButton = {
        let button = UIButton(type: .close)
        button.translatesAutoresizingMaskIntoConstraints = false
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
        
        addViews()
        setConstraints()
        addButtonActions()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        
        addViews()
        setConstraints()
        addButtonActions()
    }
    
    private func addViews() {
        self.addSubview(closeButton)
        self.addSubview(tableView)
    }
    
    private func setConstraints() {
        closeButton.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor).isActive = true
        closeButton.widthAnchor.constraint(equalToConstant: 60).isActive = true
        closeButton.heightAnchor.constraint(equalToConstant: 60).isActive = true
        closeButton.trailingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.trailingAnchor, constant: 0).isActive = true
        
        tableView.topAnchor.constraint(equalTo: self.closeButton.bottomAnchor, constant: 20).isActive = true
        tableView.bottomAnchor.constraint(equalTo: self.safeAreaLayoutGuide.bottomAnchor, constant: 0).isActive = true
        tableView.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor, constant: 0).isActive = true
        tableView.trailingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.trailingAnchor, constant: 0).isActive = true
    }
    
    private func addButtonActions() {
        closeButton.addAction(UIAction(handler: { _ in
            self.delegate?.sideViewCloseButtonDidTap()
        }), for: .touchUpInside)
    }
}
