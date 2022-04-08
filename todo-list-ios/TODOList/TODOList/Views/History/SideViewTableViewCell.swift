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
        view.spacing = 8
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
        contentView.addSubview(emojiView)
        historyView.addSubview(stackView)
        contentView.addSubview(historyView)
    }
    
    private func setConstraints() {
        stackView.topAnchor.constraint(equalTo: historyView.topAnchor).isActive = true
        stackView.leadingAnchor.constraint(equalTo: historyView.leadingAnchor).isActive = true
        stackView.trailingAnchor.constraint(equalTo: historyView.trailingAnchor).isActive = true
        stackView.bottomAnchor.constraint(equalTo: historyView.bottomAnchor).isActive = true
        
        emojiView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16).isActive = true
        emojiView.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 16).isActive = true
        emojiView.widthAnchor.constraint(equalToConstant: 40).isActive = true
        emojiView.heightAnchor.constraint(equalToConstant: 40).isActive = true
        
        historyView.leadingAnchor.constraint(equalTo: emojiView.trailingAnchor, constant: 16).isActive = true
        historyView.topAnchor.constraint(equalTo: emojiView.topAnchor).isActive = true
        historyView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16).isActive = true
        historyView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -16).isActive = true
    }
    
    func updateStackView(history: HistoryInfo) {
        stackView.nameLabel.text = "@\(history.name)"
        stackView.contentLabel.text = history.content
        stackView.timeLabel.text = "\(history.time)분 전"
    }
}
