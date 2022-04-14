//
//  CustomTableView.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/07.
//

import UIKit

class BoardTableView<Model,Cell: UITableViewCell&CellIdentifiable>: UITableView, UITableViewDelegate, UITableViewDataSource {
    
    private(set) var list : [Model] = []
    private var cellConfigurator : ((Model, Cell) -> Void)?
    var boardTableDelegate : BoardTableViewDelegate?

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
    
    
    
    func removeTodo(todo: Model) {
        guard let targetTodo = todo as? Todo else { return }
        guard let todoList = list as? [Todo] else { return }
        guard let targetIndex = todoList.firstIndex(where: { $0.id == targetTodo.id }) else { return }
        list.remove(at: targetIndex)
        deleteRows(at: [IndexPath(row: targetIndex, section: 0)], with: .automatic)
    }
    
    
    func updateCell(with todo: Model) {
        guard let targetTodo = todo as? Todo else { return }
        guard let todoList = list as? [Todo] else { return }
        guard let targetIndex = todoList.firstIndex(where: { $0.id == targetTodo.id }) else { return }
        let targetCell = cellForRow(at: IndexPath(row: targetIndex, section: 0)) as? CardCell
        targetCell?.loadCardInfo(info: targetTodo)
    }

    
    func appendTodo(todo:Model) {
        self.list.append(todo)
        insertRows(at: [IndexPath(row: list.count - 1, section: 0)], with: .automatic)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return list.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: Cell.identifier, for: indexPath) as? Cell,
              let config = cellConfigurator
        else {return UITableViewCell()}

        let card = list[indexPath.row]
        config(card, cell)
        return cell
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        guard Cell.self == CardCell.self else {return}
        if editingStyle == .delete {

            boardTableDelegate?.didTapDelete(item: list[indexPath.row])

        }
    }
    func tableView(_ tableView: UITableView, contextMenuConfigurationForRowAt indexPath: IndexPath, point: CGPoint) -> UIContextMenuConfiguration? {
        guard Cell.self == CardCell.self else {return UIContextMenuConfiguration()}
        
        let cardInfo = list[indexPath.row]
        
        
        return UIContextMenuConfiguration(identifier: nil, previewProvider: nil) { _ in
            let moveToCompleted = UIAction(title: "완료한 일로 이동") { [weak self] _ in
                guard let self = self,
                      let foucsdCard = self.list[indexPath.row] as? Todo else { return }
                self.boardTableDelegate?.didTapMoveToCompleted(foucsedCard: foucsdCard, from:indexPath.row)
            }
            
            let edit = UIAction(title: "수정하기") { _ in
                print("수정하기")
                print(cardInfo)
                self.boardTableDelegate?.DidTapEdit(item: cardInfo)
            }
       
            let delete = UIAction(title: "삭제하기", image: nil,
                attributes: [.destructive]) { [weak self] _ in
                guard let self = self ,
                      let foucsdCard = self.list[indexPath.row] as? Todo else { return }
                self.boardTableDelegate?.didTapDelete(item: foucsdCard)
               }
            return UIMenu(title : "", children: [moveToCompleted,edit,delete])
        }
    }
}


