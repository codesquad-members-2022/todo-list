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
        self.setLayout()
    }
    
    private func setLayout(){
        self.view.addSubview(sideView)
        NSLayoutConstraint.activate([
            sideView.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 0),
            sideView.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor, constant: 0),
            sideView.widthAnchor.constraint(equalToConstant: 200),
            sideView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor, constant: 0)
        ])
    }
}

extension ViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: SideViewTableViewCell.identifier, for: indexPath) as! SideViewTableViewCell
        cell.imageView?.image = UIImage(named: "emoji")
        return cell
    }
    
    
}

