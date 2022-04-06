import UIKit

class ViewController: UIViewController {
    
    private let sideView: SideView = {
        let view = SideView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.sideView.tableView.dataSource = self
        self.sideView.tableView.delegate = self
        
        addViews()
        setLayout()
    }
    
    private func addViews() {
        self.view.addSubview(sideView)
    }
    
    private func setLayout(){
        sideView.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 0).isActive = true
        sideView.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor, constant: 0).isActive = true
        sideView.widthAnchor.constraint(equalToConstant: 300).isActive = true
        sideView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor, constant: 0).isActive = true
    }
}

extension ViewController: UITableViewDataSource, UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 15
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: SideViewTableViewCell.identifier, for: indexPath) as! SideViewTableViewCell
        cell.emojiView.image = UIImage(named: "emoji")
        
        let history = HistoryInfo(name: "Selina", content: "이제 자러갑니당", time: "0")
        cell.stackView.nameLabel.text = "@\(history.name)"
        cell.stackView.contentLabel.text = history.content
        cell.stackView.timeLabel.text = "\(history.time)분 전"
        return cell
    }
}
