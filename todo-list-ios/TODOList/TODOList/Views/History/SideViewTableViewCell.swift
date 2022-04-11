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
    
    var historyStackView: UIStackView = {
        let view = UIStackView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.axis = .vertical
        view.spacing = 8
        view.alignment = .leading
        view.distribution = .equalSpacing
        return view
    }()
    
    var nameLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: FontFactory.normal, size: 16)
        return label
    }()
    
    var contentLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: FontFactory.bold, size: 16)
        label.numberOfLines = 0
        return label
    }()
    
    var timeLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: FontFactory.normal, size: 14)
        label.textColor = UIColor(named: ColorAsset.gray3)
        return label
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
        historyView.addSubview(historyStackView)
        historyStackView.addArrangedSubview(nameLabel)
        historyStackView.addArrangedSubview(contentLabel)
        historyStackView.addArrangedSubview(timeLabel)
        contentView.addSubview(historyView)
    }
    
    private func setConstraints() {
        historyStackView.topAnchor.constraint(equalTo: historyView.topAnchor).isActive = true
        historyStackView.leadingAnchor.constraint(equalTo: historyView.leadingAnchor).isActive = true
        historyStackView.trailingAnchor.constraint(equalTo: historyView.trailingAnchor).isActive = true
        historyStackView.bottomAnchor.constraint(equalTo: historyView.bottomAnchor).isActive = true
        
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
        nameLabel.text = "@\(history.name)"
        contentLabel.text = history.content
        timeLabel.text = "\(history.time)분 전"
    }
}
