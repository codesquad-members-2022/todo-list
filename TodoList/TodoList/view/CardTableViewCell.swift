//
//  CardTableViewCell.swift
//  TodoList
//
//  Created by Bibi on 2022/04/07.
//

import UIKit

class CardTableViewCell: UITableViewCell {
    lazy var view = UIView()
    lazy var titleLabel: UILabel = {
        let label = UILabel()
        label.text = "title"
        label.font = UIFont.boldSystemFont(ofSize: 22)
        return label
    }()
    
    lazy var contentLabel: UILabel = {
        let label = UILabel()
        label.text = "Content"
        label.font = UIFont.boldSystemFont(ofSize: 18)
        return label
    }()
    
    lazy var writerLabel: UILabel = {
        let label = UILabel()
        label.text = "writer"
        label.font = UIFont.boldSystemFont(ofSize: 14)
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
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        contentView.addSubview(view)
        view.addSubview(titleLabel)
        view.addSubview(contentLabel)
        view.addSubview(writerLabel)
        setPropertiesAutoLayout()
        
    }
    
    func setCellUIProperty(title: String, content: String, writer: String){
        titleLabel.text = title
        contentLabel.text = content
        writerLabel.text = writer
    }
    
    private func setPropertiesAutoLayout() {
        view.translatesAutoresizingMaskIntoConstraints = false
        view.heightAnchor.constraint(equalTo: contentView.heightAnchor).isActive = true
        view.widthAnchor.constraint(equalTo: contentView.widthAnchor).isActive = true
        view.centerXAnchor.constraint(equalTo: contentView.centerXAnchor).isActive = true
        

        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        titleLabel.widthAnchor.constraint(equalTo: self.contentView.widthAnchor, constant: 0).isActive = true
        titleLabel.heightAnchor.constraint(equalToConstant: 30).isActive = true
    }
}
