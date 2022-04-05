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
        return view
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        
        setLayout()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    private func setLayout(){
        self.contentView.addSubview(emojiView)
        self.contentView.addSubview(historyView)
        
        NSLayoutConstraint.activate([
            emojiView.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor),
            emojiView.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor),
            emojiView.widthAnchor.constraint(equalToConstant: 50),
            emojiView.heightAnchor.constraint(equalToConstant: 50),
            
            historyView.leadingAnchor.constraint(equalTo: self.emojiView.trailingAnchor),
            historyView.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor),
            historyView.trailingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.trailingAnchor, constant: 0),
            historyView.heightAnchor.constraint(equalToConstant: 300)
        ])
    }
    
}
