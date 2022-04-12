//
//  HistoryTableViewCell.swift
//  TodoList
//
//  Created by Bibi on 2022/04/12.
//

import Foundation
import UIKit

class HistoryTableViewCell: UITableViewCell {
    
    static let identifier = "HistoryTableViewCell"
    
    lazy var view = UIView()
    
    lazy var emojiLabel: UILabel = {
        let label = UILabel()
        label.text = "🥳"
        label.font = UIFont.systemFont(ofSize: 30)
        return label
    }()
    
    lazy var writerLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 18)
        return label
    }()
    
    lazy var titleFromToLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 22)
        return label
    }()
    
    lazy var timestampLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 15)
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
        view.addSubview(emojiLabel)
        view.addSubview(titleFromToLabel)
        view.addSubview(writerLabel)
        view.addSubview(timestampLabel)
        contentView.addSubview(view)
        setPropertiesAutoLayout()
        setCellUIProperty()
    }
    
    private func setCellUIProperty() {
        contentView.backgroundColor = .yellow
        view.backgroundColor = .white
    }
    
    func setCellUIData(title: String, writer: String, time: Date) {
        self.titleFromToLabel.text = title
        self.writerLabel.text = writer
        self.timestampLabel.text = time.description
    }
    
    private func setPropertiesAutoLayout() {
        view.translatesAutoresizingMaskIntoConstraints = false
        view.heightAnchor.constraint(equalToConstant: CGFloat(200)).isActive = true
        view.topAnchor.constraint(equalTo: contentView.topAnchor).isActive = true
        view.leadingAnchor.constraint(equalTo: contentView.leadingAnchor).isActive = true
        
        emojiLabel.translatesAutoresizingMaskIntoConstraints = false
        emojiLabel.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 10).isActive = true
        emojiLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 15).isActive = true
        //emojiLabel.trailingAnchor.constraint(equalTo: writerLabel.trailingAnchor, constant: -15).isActive = true
        emojiLabel.heightAnchor.constraint(equalToConstant: 20).isActive = true
        
        writerLabel.translatesAutoresizingMaskIntoConstraints = false
        writerLabel.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 10).isActive = true
        writerLabel.leadingAnchor.constraint(equalTo: emojiLabel.trailingAnchor, constant: 15).isActive = true
        //writerLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -10).isActive = true
        
    }

}
