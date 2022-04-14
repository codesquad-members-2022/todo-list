//
//  CustomTableView.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/07.
//

import UIKit

class BoardTableView<Model,Cell: UITableViewCell&CellIdentifiable>: UITableView, UITableViewDelegate, UITableViewDataSource {
    
    private var list : [Model]?
    private var cellConfigurator : ((Model, Cell) -> Void)?
    var BoardTableDelegate : BoardTableViewDelegate?

    override init(frame: CGRect, style: UITableView.Style) {
        super.init(frame: frame, style: style)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }

    convenience init(frame: CGRect, style: UITableView.Style, list:[Model], cellConfigurator: @escaping (Model, Cell) -> Void) {
        self.init(frame: frame, style: style)
        self.list = list
        self.cellConfigurator = cellConfigurator
        self.delegate = self
        self.dataSource = self
        self.register(Cell.self, forCellReuseIdentifier: Cell.identifier)
        self.translatesAutoresizingMaskIntoConstraints = false
        setupStyle()
    }
    
    

    private func setupStyle() {
        self.separatorStyle = .none        
        self.backgroundColor = .secondarySystemBackground
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard let count = list?.count else {return 0}
        return count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: Cell.identifier, for: indexPath) as? Cell,
              let list = list,
              let config = cellConfigurator
        else {return UITableViewCell()}

        let card = list[indexPath.row]
        config(card, cell)
        return cell
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        guard Cell.self == CardCell.self, let list = list else {return}
        if editingStyle == .delete {
            BoardTableDelegate?.DidTapDelete(item: list[indexPath.row])
        }
    }
    func tableView(_ tableView: UITableView, contextMenuConfigurationForRowAt indexPath: IndexPath, point: CGPoint) -> UIContextMenuConfiguration? {
        guard Cell.self == CardCell.self, let list = list else {return UIContextMenuConfiguration()}
        
        return UIContextMenuConfiguration(identifier: nil, previewProvider: nil) { _ in
            let moveToCompleted = UIAction(title: "완료한 일로 이동") { _ in
                print("완료한 일로 이동")
            }
            
            let edit = UIAction(title: "수정하기") { _ in
                print("수정하기")
            }
       
            let delete = UIAction(title: "삭제하기",
                image: nil,
                attributes: [.destructive]) { action in
                print("수정하기")
               }
            return UIMenu(title : "", children: [moveToCompleted,edit,delete])
        }
    }
}


