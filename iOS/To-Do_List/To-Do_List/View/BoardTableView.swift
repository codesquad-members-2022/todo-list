//
//  CustomTableView.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/07.
//

import UIKit

class BoardTableView<Model,Cell: UITableViewCell&CellIdentifiable>: UITableView, UITableViewDelegate, UITableViewDataSource {
    
    private var list : [Model]
    private let cellConfigurator : (Model, Cell) -> Void

    init(frame: CGRect, style: UITableView.Style, list:[Model], cellConfigurator: @escaping (Model, Cell) -> Void) {
        self.list = list
        self.cellConfigurator = cellConfigurator
        super.init(frame: frame, style: style)
        self.delegate = self
        self.dataSource = self
        self.register(Cell.self, forCellReuseIdentifier: Cell.identifier)
        self.translatesAutoresizingMaskIntoConstraints = false
        setupStyle()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func setupStyle() {
        self.separatorStyle = .none        
        self.backgroundColor = .secondarySystemBackground
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        list.count
        10
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: Cell.identifier, for: indexPath) as? Cell else {return UITableViewCell()}
//        let card = list[indexPath.row]
//        cellConfigurator(card, cell)
        return cell
    }
}

