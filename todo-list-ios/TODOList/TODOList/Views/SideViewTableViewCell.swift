import UIKit

class SideViewTableViewCell: UITableViewCell {
    
    static let identifier: String = "sideViewTableViewCell"
    
    let emojiView: UIImageView = {
        let view = UIImageView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    let historyView: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    var stackView: HistoryStackView = {
        let view = HistoryStackView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.axis = .vertical
        view.spacing = 3
        view.alignment = .leading
        view.distribution = .equalSpacing
        return view
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        
        addViews()
        setConstraints()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        
        addViews()
        setConstraints()
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    private func addViews() {
        self.contentView.addSubview(emojiView)
        self.historyView.addSubview(stackView)
        self.contentView.addSubview(historyView)
    }
    
    private func setConstraints() {
        self.stackView.topAnchor.constraint(equalTo: self.historyView.topAnchor).isActive = true
        self.stackView.leadingAnchor.constraint(equalTo: self.historyView.leadingAnchor).isActive = true
        self.stackView.trailingAnchor.constraint(equalTo: self.historyView.trailingAnchor).isActive = true
        self.stackView.bottomAnchor.constraint(equalTo: self.historyView.bottomAnchor).isActive = true
        
        emojiView.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor).isActive = true
        emojiView.topAnchor.constraint(equalTo: self.contentView.topAnchor).isActive = true
        emojiView.widthAnchor.constraint(equalToConstant: 50).isActive = true
        emojiView.heightAnchor.constraint(equalToConstant: 50).isActive = true
        
        historyView.leadingAnchor.constraint(equalTo: self.emojiView.trailingAnchor).isActive = true
        historyView.topAnchor.constraint(equalTo: self.contentView.topAnchor).isActive = true
        historyView.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor).isActive = true
        historyView.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor).isActive = true
    }
    
}
