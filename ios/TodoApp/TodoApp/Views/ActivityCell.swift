//
//  ActivityCell.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/06.
//

import UIKit

class ActivityCell: UITableViewCell {
    static let identifier = "ActivityCell"
    
    private let horizontalStackView: UIStackView = {
        let stack = UIStackView()
        stack.axis = .horizontal
        stack.alignment = .top
        stack.spacing = 20
        stack.translatesAutoresizingMaskIntoConstraints = false
        return stack
    }()
    
    private let verticalStackView: UIStackView = {
        let stack = UIStackView()
        stack.axis = .vertical
        stack.spacing = 10
        return stack
    }()
    
    private let thumnail: UILabel = {
        let label = UILabel(frame: .zero)
        label.text = "🥳"
        label.textAlignment = .center
        label.font = .systemFont(ofSize: 36)
        
        return label
    }()
    
    private let headerLabel = UILabel()
    
    private let bodyLabel: UILabel = {
        let label = UILabel(frame: .zero)
        label.numberOfLines = 0
        return label
    }()
    
    private let footerLabel = UILabel()
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.configureCell()
    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        self.configureCell()
    }

    private func configureCell() {
        self.setBackgroundColor(UIColor(red: 109/255, green: 208/255, blue: 40/255, alpha: 0.1))
        self.headerLabel.text = "@Alex"
        self.bodyLabel.text = "HTML/CSS 공부하기를 해야할 일에서 하고 있는 일로 이동하였습니다."
        self.footerLabel.text = "1분 전"
        
        self.verticalStackView.addArrangedSubview(self.headerLabel)
        self.verticalStackView.addArrangedSubview(self.bodyLabel)
        self.verticalStackView.addArrangedSubview(self.footerLabel)
        
        self.horizontalStackView.addArrangedSubview(self.thumnail)
        self.horizontalStackView.addArrangedSubview(self.verticalStackView)
        self.contentView.addSubview(self.horizontalStackView)
        
        let horizontalStackViewConstraints = [
            self.horizontalStackView.topAnchor.constraint(equalTo: self.contentView.topAnchor,constant:  20),
            self.horizontalStackView.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor, constant: -20),
            self.horizontalStackView.rightAnchor.constraint(equalTo: self.contentView.rightAnchor, constant: -20),
            self.horizontalStackView.leftAnchor.constraint(equalTo: self.contentView.leftAnchor, constant:  20)
        ]
        
        NSLayoutConstraint.activate(horizontalStackViewConstraints)
        
    }
    
    func setHeaderText(_ text: String) {
        self.headerLabel.text = text
    }
    
    func setFooterText(_ text: String) {
//        self.footerLabel.text = text
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        dateFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone?
        let oldDate = dateFormatter.date(from: text)
        guard let oldDate = oldDate else {
            return
        }
        let timeInterval = oldDate.timeIntervalSinceNow
        var result = ""
        let absInterval = abs(timeInterval)
        switch absInterval {
        case 0..<60:
            result = "1분 전"
        case 60..<3600:
            let minute = absInterval / 60
            result = "\(Int(minute))분 전"
        case 3600..<86400:
            let hour = absInterval / 3600
            result = "\(Int(hour))시간 전"
        case 86400..<Double.greatestFiniteMagnitude :
            let day = absInterval / 86400
            result = "\(Int(day))일 전"
        default:
            result = "\(Int(absInterval))초 전"
        }
        self.footerLabel.text = result
    }
    // MARK: - 중요 키워드 집중
    func setBodyText(_ activity: ActivityBody) {
        self.bodyLabel.text = String(activity.text)
        guard let targetText = bodyLabel.text else { return }
        let fontSize = UIFont.boldSystemFont(ofSize: 17)
        let attributedStr = NSMutableAttributedString(string: targetText)
        let item = activity.activity
        var actionString: String {
            var string = ""
            switch item.action {
            case "ADD":
                string = "등록"
            case "MOVE":
                string = "이동"
            case "REMOVE":
                string = "삭제"
            case "UPDATE":
                string = "수정"
            default:
                string = "default"
            }
            return string
        }
        attributedStr.addAttribute(.font, value: fontSize, range: (attributedStr.string as NSString).range(of: String(item.cardTitle)))
        attributedStr.addAttribute(.font, value: fontSize, range: (attributedStr.string as NSString).range(of: String(item.oldColumnName)))
        attributedStr.addAttribute(.font, value: fontSize, range: (attributedStr.string as NSString).range(of: String(item.newColumnName)))
        attributedStr.addAttribute(.font, value: fontSize, range: (attributedStr.string as NSString).range(of: actionString))
        bodyLabel.attributedText = attributedStr
    }
    
    func setBackgroundColor(_ color: UIColor) {
        self.contentView.backgroundColor = color
    }
}
