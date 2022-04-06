import UIKit

class HistoryStackView: UIStackView {

    var nameLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: "Apple SD Gothic Neo Bold", size: 30)
        label.text = "asfasdfa"
        return label
    }()
    
    var contentLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: "Apple SD Gothic Neo Bold", size: 15)
        label.text = """
어둠 속에 파고든
내 안에 날 가두던
시간은 이미 지나고
It fades away
하염없이 떠돌다
낯선 벽에 부딪혀 버린
Oh 이제 난 어디로
Please hold my hand
        하나둘씩 열어
        감춰왔던 나를 깨워
        잊으려 했던 결국엔 늘
        잊을 수 없었던 꿈들
"""
        label.numberOfLines = 0
        return label
    }()
    
    var timeLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: "Apple SD Gothic Neo Bold", size: 50)
        label.text = "time"
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
