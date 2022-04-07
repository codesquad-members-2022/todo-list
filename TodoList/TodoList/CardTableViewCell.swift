//
//  CardTableViewCell.swift
//  TodoList
//
//  Created by Bibi on 2022/04/07.
//

import UIKit

class CardTableViewCell: UITableViewCell {

    lazy var titleLabel: UILabel = {
        let label = UILabel()
        label.text = "title"
        label.font = UIFont.boldSystemFont(ofSize: 32)
        return label
    }()
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
//    init() {
//        self.setPropertiesAutoLayout()
//    }
    
    func setPropertiesAutoLayout() {
        titleLabel.widthAnchor.constraint(equalTo: self.contentView.widthAnchor, constant: 0).isActive = true
        titleLabel.heightAnchor.constraint(equalToConstant: 30).isActive = true
    }
}
