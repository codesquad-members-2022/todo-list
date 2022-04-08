import UIKit

class HistoryStackView: UIStackView {

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
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        addViews()
    }
    
    required init(coder: NSCoder) {
        super.init(coder: coder)
        
        addViews()
    }
    
    private func addViews() {
        addArrangedSubview(nameLabel)
        addArrangedSubview(contentLabel)
        addArrangedSubview(timeLabel)
    }
}
