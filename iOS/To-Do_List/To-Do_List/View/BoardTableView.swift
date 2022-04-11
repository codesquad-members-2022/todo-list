//
//  CustomTableView.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/07.
//

import UIKit

class BoardTableView: UITableView {
    
    override init(frame: CGRect, style: UITableView.Style) {
        super.init(frame: frame, style: .plain)
        setupStyle()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupStyle()
    }
    
    private func setupStyle() {
        self.separatorStyle = .none        
        self.register(CardCell.self, forCellReuseIdentifier: CardCell.identifier)
        self.backgroundColor = .secondarySystemBackground
        self.rowHeight = UITableView.automaticDimension
    }
}


