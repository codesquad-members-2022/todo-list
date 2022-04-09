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
    
    let historyTableView: UITableView = {
        let tableView = UITableView()
        tableView.backgroundColor = .clear
        //현재 아래와 같이 구분선 설정을 없애도 사이드 뷰 내부의 테이블 뷰에 적용되지 않음
        tableView.separatorStyle = .none
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.register(SideViewTableViewCell.self, forCellReuseIdentifier: SideViewTableViewCell.identifier)
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
        addSubview(emptyView)
        emptyView.addSubview(closeButton)
        addSubview(historyTableView)
    }
    
    private func setConstraints() {
        emptyView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        emptyView.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        emptyView.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
        emptyView.heightAnchor.constraint(equalTo: heightAnchor, multiplier: 1/8.5) .isActive = true
        
        closeButton.centerYAnchor.constraint(equalTo: emptyView.centerYAnchor).isActive = true
        closeButton.widthAnchor.constraint(equalToConstant: 20).isActive = true
        closeButton.heightAnchor.constraint(equalToConstant: 20).isActive = true
        closeButton.trailingAnchor.constraint(equalTo: emptyView.trailingAnchor, constant: -50).isActive = true
        
        historyTableView.topAnchor.constraint(equalTo: emptyView.bottomAnchor, constant: 20).isActive = true
        historyTableView.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true
        historyTableView.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        historyTableView.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
    }

}
