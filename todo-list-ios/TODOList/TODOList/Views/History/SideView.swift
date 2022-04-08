import Foundation
import UIKit

class SideView: UIView {
    
    weak var delegate: SideViewDelegate?
    
    private let emptyView: UIView = {
       let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.backgroundColor = .white
        return view
    }()
    
    private lazy var closeButton: UIButton = {
        let button = UIButton()
        button.setBackgroundImage(UIImage(named: "close"), for: .normal)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.addAction(UIAction(handler: { _ in
            self.delegate?.sideViewCloseButtonDidTap()
        }), for: .touchUpInside)
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
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        
        addViews()
        setConstraints()
    }
    
    private func addViews() {
        addSubview(emptyView)
        emptyView.addSubview(closeButton)
        addSubview(tableView)
    }
    
    private func setConstraints() {
        emptyView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        emptyView.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        emptyView.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
        emptyView.heightAnchor.constraint(equalToConstant: 60).isActive = true
        
        closeButton.centerYAnchor.constraint(equalTo: emptyView.centerYAnchor).isActive = true
        closeButton.widthAnchor.constraint(equalToConstant: 20).isActive = true
        closeButton.heightAnchor.constraint(equalToConstant: 20).isActive = true
        closeButton.trailingAnchor.constraint(equalTo: emptyView.trailingAnchor, constant: -50).isActive = true
        
        tableView.topAnchor.constraint(equalTo: emptyView.bottomAnchor, constant: 20).isActive = true
        tableView.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true
        tableView.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        tableView.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
    }

}
