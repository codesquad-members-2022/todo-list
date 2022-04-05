import UIKit

class SideViewTableViewCell: UITableViewCell {
    
    static let identifier: String = "sideViewTableViewCell"
    
    let emojiView: UIImageView = {
        let view = UIImageView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    private func setLayout(){
        self.addSubview(emojiView)
        NSLayoutConstraint.activate([
            emojiView.leadingAnchor.constraint(equalTo: self.safeAreaLayoutGuide.leadingAnchor),
            emojiView.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor),
            emojiView.widthAnchor.constraint(equalToConstant: 50),
            emojiView.heightAnchor.constraint(equalToConstant: 50)
        ])
    }
    
}
