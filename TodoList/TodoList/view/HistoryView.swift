//
//  HistoryView.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/11.
//

import Foundation
import UIKit

class HistoryView: UIView{
    private let closeButton: UIButton = {
        let button = UIButton()
        button.setImage(UIImage(systemName: "xmark"), for: .normal)
        button.setTitleColor(.black, for: .normal)
        return button
    }()
    
    var actionDelegate: HistoryViewAction?
    
    private let historyTableView = UITableView()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setUI()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUI()
    }
    
    private func setUI(){
        backgroundColor = .white
        addSubview(closeButton)
        addSubview(historyTableView)
        setUIConstraint()
        setCloseButtonAction()
        historyTableView.backgroundColor = .brown
    }
    
    private func setUIConstraint(){
        setCloseButtonConstraint()
        setHistoryTableViewConstraint()
    }
    
    private func setCloseButtonConstraint() {
        closeButton.translatesAutoresizingMaskIntoConstraints = false
        closeButton.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor, constant: 10).isActive = true
        closeButton.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor, constant: -20).isActive = true
        closeButton.widthAnchor.constraint(equalToConstant: 30).isActive = true
        closeButton.heightAnchor.constraint(equalToConstant: 30).isActive = true
    }
    
    private func setHistoryTableViewConstraint() {
        historyTableView.translatesAutoresizingMaskIntoConstraints = false
        historyTableView.topAnchor.constraint(equalTo: closeButton.bottomAnchor, constant: 10).isActive = true
        historyTableView.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -10).isActive = true
        historyTableView.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 10).isActive = true
        historyTableView.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -10).isActive = true
    }
    
    private func setCloseButtonAction() {
        closeButton.addAction(UIAction {
            [weak self] (action: UIAction) in
            guard let self = self else { return }
            self.actionDelegate?.closeButtonTapped()
        }, for: .touchUpInside)
    }
}
