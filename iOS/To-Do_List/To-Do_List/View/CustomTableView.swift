//
//  CustomTableView.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/07.
//

import UIKit

class CustomTableView: UITableView {
    
    override init(frame: CGRect, style: UITableView.Style) {
        super.init(frame: frame, style: style)
        setupStyle()
        setConstraints()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupStyle()
        setConstraints()
    }
    
    private func setupStyle() {
        self.backgroundColor = .white
        self.separatorStyle = .none
        self.translatesAutoresizingMaskIntoConstraints = false
    }
    
    private func setConstraints() {
        guard let superView = self.superview else{return}
        NSLayoutConstraint.activate([
            self.trailingAnchor.constraint(equalTo: superView.trailingAnchor),
            self.leadingAnchor.constraint(equalTo: superView.leadingAnchor)
        ])
    }
}
