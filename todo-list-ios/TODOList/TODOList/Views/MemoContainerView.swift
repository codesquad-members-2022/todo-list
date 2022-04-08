import Foundation
import UIKit

class MemoContainerView: UIView {
    
    let horizontalStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .horizontal
        stackView.spacing = 10
        stackView.alignment = .fill
        return stackView
    }()
    
    let categoryLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: FontFactory.normal, size: 18)
        label.textColor = UIColor(named: ColorAsset.black)
        return label
    }()
    
    let countLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: FontFactory.bold, size: 14)
        label.textColor = UIColor(named: ColorAsset.black)
        label.backgroundColor = UIColor(named: ColorAsset.gray4)
        return label
    }()
    
    let button: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setBackgroundImage(UIImage(named: "add"), for: .normal)
        return button
    }()
    
    private func addViews() {
        horizontalStackView.addArrangedSubview(categoryLabel)
        horizontalStackView.addArrangedSubview(countLabel)
        horizontalStackView.addArrangedSubview(button)
        addSubview(horizontalStackView)
    }
}
