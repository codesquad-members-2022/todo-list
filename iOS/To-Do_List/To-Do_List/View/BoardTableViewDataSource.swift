//
//  BoardTableViewDataSource.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/11.
//

import UIKit

class BoardTableViewDataSource: NSObject, UITableViewDataSource {
    
    typealias CellConfigurator = (Todo, UITableViewCell) -> Void
    
     var list : [Todo]
    
    private let resueIdentifier : String
    private let cellConfigurator : CellConfigurator
    
    
    init(list: [Todo], reuseIdentifier : String , cellConfigurator: @escaping CellConfigurator){
        self.list = list
        self.resueIdentifier = reuseIdentifier
        self.cellConfigurator = cellConfigurator
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        list.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: CardCell.identifier, for: indexPath)
        let card = list[indexPath.row]
        cellConfigurator(card, cell)
        return cell
    }

   
}
