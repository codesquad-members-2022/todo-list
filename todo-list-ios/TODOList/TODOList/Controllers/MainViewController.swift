import UIKit

final class MainViewController: UIViewController {
    
    lazy var sideMenuButton: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setBackgroundImage(UIImage(systemName: "list.bullet.circle.fill"), for: .normal)
        button.addAction(UIAction(handler: { _ in
            self.showSideView()
        }), for: .touchUpInside)
        return button
    private var headerView: HeaderView = {
        let view = HeaderView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    private let sideView: SideView = {
        let view = SideView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.sideView.delegate = self
        self.sideView.tableView.dataSource = self
        self.sideView.tableView.delegate = self
        
        addViews()
        setLayout()
    }
    
    /// 초기 뷰 설정. 초기 뷰는 sideMenuButton이 보여지는 상태.
    private func addViews() {
        self.view.addSubview(sideMenuButton)
    }
    
    /// sideMenuButton Layout 설정
    private func setLayout(){
        sideMenuButton.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor).isActive = true
        sideMenuButton.widthAnchor.constraint(equalToConstant: 60).isActive = true
        sideMenuButton.heightAnchor.constraint(equalToConstant: 60).isActive = true
        sideMenuButton.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor).isActive = true
        headerView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor).isActive = true
        headerView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor).isActive = true
        headerView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor).isActive = true
        headerView.heightAnchor.constraint(equalTo: view.safeAreaLayoutGuide.heightAnchor, multiplier: 0.08).isActive = true
    }
    
    /// sideMenuButton 클릭 시 SideView를 보여준다. 
    private func showSideView() {
        self.sideMenuButton.removeFromSuperview()
        self.view.addSubview(sideView)
        
        sideView.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor).isActive = true
        sideView.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor).isActive = true
        sideView.widthAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.widthAnchor, multiplier: 0.3).isActive = true
        sideView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor).isActive = true
    }
    
    /// SideMenu에 있는 닫기 버튼 클릭 시, sideView를 제거하고 SideMenuButton을 보여줌.
    private func hideSideView() {
        UIView.transition(with: self.sideView, duration: 0.25) {
            self.sideView.removeFromSuperview()
        }
        
        self.view.addSubview(sideMenuButton)
        setLayout()
    }
}


extension MainViewController: SideViewDelegate {
    func sideViewCloseButtonDidTap() {
        hideSideView()
    }
}


extension MainViewController: UITableViewDataSource, UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 15
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: SideViewTableViewCell.identifier, for: indexPath) as? SideViewTableViewCell else {
            return UITableViewCell()
        }
        cell.emojiView.image = UIImage(named: "emoji")
        
        let history = HistoryInfo(name: "Selina", content: "이제 자러갑니당", time: "0")
        cell.updateStackView(history: history)
        return cell
    }
}
