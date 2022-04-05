import UIKit

class HistoryStackView: UIStackView {

    let nameLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    let contentLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    let timeLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    init(name: String, content: String, time: String) {
        nameLabel.text = "@\(name)"
        contentLabel.text = content
        timeLabel.text = "\(time)분 전"
        
        super.init()
        
        setLayout()
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        setLayout()
    }
    
    required init(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setLayout() {
        self.axis = .vertical
        self.spacing = 3
        self.distribution = .fill
        
        addSubview(nameLabel)
        addSubview(contentLabel)
        addSubview(timeLabel)
        
        NSLayoutConstraint.activate([
            nameLabel.widthAnchor.constraint(equalTo: self.widthAnchor),
            nameLabel.heightAnchor.constraint(equalToConstant: 50),
            contentLabel.widthAnchor.constraint(equalTo: self.widthAnchor),
            contentLabel.heightAnchor.constraint(equalToConstant: 50),
            timeLabel.widthAnchor.constraint(equalTo: self.widthAnchor),
            timeLabel.heightAnchor.constraint(equalToConstant: 50)
        ])
    }
}
