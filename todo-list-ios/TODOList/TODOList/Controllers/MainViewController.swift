import UIKit

final class MainViewController: UIViewController {
    
    private var headerView: HeaderView = {
        let view = HeaderView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    private var sideView: SideView = {
        let view = SideView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    private var memoCanvasViewController: MemoCanvasViewController?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = UIColor(named: ColorAsset.gray6)
        
        let memoCanvasViewController = MemoCanvasViewController()
        addChild(memoCanvasViewController)
        view.addSubview(memoCanvasViewController.view) // 안하면 뷰 안 보임
        memoCanvasViewController.didMove(toParent: self) // 종속관계만 생김
        self.memoCanvasViewController = memoCanvasViewController
        
        self.headerView.delegate = self
        
        self.sideView.delegate = self
        self.sideView.tableView.dataSource = self
        self.sideView.tableView.delegate = self
        
        addViews()
        setLayout()
    }
    
    /// 초기 뷰 설정. 초기 뷰는 sideMenuButton이 보여지는 상태.
    private func addViews() {
        self.view.addSubview(headerView)
    }
    
    private func setLayout(){
        headerView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor).isActive = true
        headerView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor).isActive = true
        headerView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor).isActive = true
        headerView.heightAnchor.constraint(equalTo: view.safeAreaLayoutGuide.heightAnchor, multiplier: 0.08).isActive = true
        
        memoCanvasViewController?.view.topAnchor.constraint(equalTo: headerView.bottomAnchor).isActive = true
        memoCanvasViewController?.view.leadingAnchor.constraint(equalTo: headerView.leadingAnchor).isActive = true
        memoCanvasViewController?.view.trailingAnchor.constraint(equalTo: headerView.trailingAnchor).isActive = true
        memoCanvasViewController?.view.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor).isActive = true
    }
    
    /// sideMenuButton 클릭 시 SideView를 보여준다. 
    private func showSideView() {
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
    
        setLayout()
    }
}

extension MainViewController: HeaderViewDelegate{
    func headerViewButtonDidTap() {
        showSideView()
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
