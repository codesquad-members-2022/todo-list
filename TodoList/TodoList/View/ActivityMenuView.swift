//
//  ActivityMenuView.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/11.
//

import UIKit

final class ActivityMenuView: UIView {
    private var closeButton: UIButton!
    var tableView: UITableView!
    var delegate: ActivityViewDelegate?
    
    override init(frame: CGRect){
        super.init(frame: frame)
        setAttributes()
    }
    
    required init?(coder: NSCoder){
        super.init(coder: coder)
        setAttributes()
    }
}

private extension ActivityMenuView{
    func setAttributes(){
        self.backgroundColor = .white
        configureCloseButton()
        configureTableView()
    }
    
    func configureCloseButton(){
        let buttonSize: CGFloat = 24.0
        closeButton = UIButton()
        closeButton.tintColor = .black
        closeButton.setImage(UIImage(named: "close"), for: .normal)
        
        self.addSubview(closeButton)
        closeButton.translatesAutoresizingMaskIntoConstraints = false
        closeButton.heightAnchor.constraint(equalToConstant: buttonSize).isActive = true
        closeButton.widthAnchor.constraint(equalToConstant: buttonSize).isActive = true
        closeButton.topAnchor.constraint(equalTo: self.topAnchor, constant: 80).isActive = true
        closeButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -48).isActive = true
        
        closeButton.addTarget(self, action: #selector(dismissActivityView), for: .touchUpInside)
    }
    
    func configureTableView(){
        let tableViewWidth: CGFloat = 332.0
        
        tableView = UITableView()
        self.addSubview(tableView)
        
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.topAnchor.constraint(equalTo: closeButton.bottomAnchor, constant: 38.75).isActive = true
        tableView.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -80).isActive = true
        tableView.widthAnchor.constraint(equalToConstant: tableViewWidth).isActive = true
        tableView.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
    }
    
    @objc
    func dismissActivityView(){
        self.delegate?.closeButtonDidTouched()
    }
}
