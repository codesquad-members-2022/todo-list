import UIKit

class HistoryStackView: UIStackView {

    var nameLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: "Apple SD Gothic Neo", size: 16)
        return label
    }()
    
    var contentLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: "Apple SD Gothic Neo Bold", size: 16)
        label.numberOfLines = 0
        return label
    }()
    
    var timeLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: "Apple SD Gothic Neo", size: 14)
        label.textColor = UIColor(hex: "#828282")
        return label
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        addViews()
    }
    
    required init(coder: NSCoder) {
        super.init(coder: coder)
        
        addViews()
    }
    
    private func addViews() {
        self.addArrangedSubview(nameLabel)
        self.addArrangedSubview(contentLabel)
        self.addArrangedSubview(timeLabel)
    }
}
