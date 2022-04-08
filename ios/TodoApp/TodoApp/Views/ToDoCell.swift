//
//  ToDoCell.swift
//  TodoApp
//
//  Created by YEONGJIN JANG on 2022/04/07.
//

import UIKit

class ToDoCell: UITableViewCell {
    static let identifier = "ToDoCell"

    @IBOutlet weak var cardView: UIView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        configureUI()
    }

   
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureUI()
    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        configureUI()
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }
    
    private func configureUI() {
        self.cardView?.layer.cornerRadius = 10
    }

}
