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
        label.font = UIFont.systemFont(ofSize: 20)
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
        contentView.addSubview(view)
        view.addSubview(emojiLabel)
        view.addSubview(titleFromToLabel)
        view.addSubview(writerLabel)
        view.addSubview(timestampLabel)
        setPropertiesAutoLayout()
        setCellUIProperty()
    }
    
    private func setCellUIProperty() {
        
    }
    
    private func setPropertiesAutoLayout() {
        view.translatesAutoresizingMaskIntoConstraints = false
    }

}
