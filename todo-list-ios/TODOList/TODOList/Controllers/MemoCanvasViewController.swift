import UIKit

final class MemoCanvasViewController: UIViewController {
    
    private var memoCanvasView: MemoCanvasView = {
        let view = MemoCanvasView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    private (set) var memoTableViewControllers: [MemoStatus: MemoContainerViewController] = [:]
    private (set) var memoManager: MemoManager = MemoManager()
    
    override func didMove(toParent parent: UIViewController?) {
        super.didMove(toParent: parent)
        view = memoCanvasView
        
        initProperties()
        initNotificationCenter()
        setLayout()
        subscribeObserver()
    }
    
    private func initProperties() {
        for containerType in MemoStatus.allCases {
            addTableViewController(containerType: containerType)
        }
    }
    
    private func initNotificationCenter() {
        NotificationCenter.default.addObserver(self, selector: #selector(addingMemoCompleted(_:)), name: .MemoDidAdd, object: memoManager)
    }
    
    @objc func addingMemoCompleted(_ notification: Notification) {
        
    }
    
        let tableViewController = MemoContainerViewController(containerType: containerType)
        memoTableViewControllers[containerType] = tableViewController
        
        addChild(tableViewController)
        memoCanvasView.memoContainerStackView.addArrangedSubview(tableViewController.view)
        tableViewController.didMove(toParent: self)
        
        addTableViewConfigurations(tableViewController: tableViewController)
    }
    
    private func addTableViewConfigurations(tableViewController: MemoContainerViewController) {
        tableViewController.memoContainerView.tableView.dataSource = tableViewController
        tableViewController.memoContainerView.tableView.delegate = tableViewController
        tableViewController.memoContainerView.tableView.dragDelegate = tableViewController
        tableViewController.memoContainerView.tableView.dropDelegate = tableViewController
        tableViewController.memoContainerView.tableView.dragInteractionEnabled = true
        tableViewController.memoContainerView.tableView.register(MemoTableViewCell.self, forCellReuseIdentifier: MemoTableViewCell.identifier)
    }
    
    private func setLayout() {
        memoCanvasView.addSubview(memoCanvasView.memoContainerStackView)
        memoCanvasView.memoContainerStackView.leadingAnchor.constraint(equalTo: memoCanvasView.leadingAnchor, constant: 48).isActive = true
        memoCanvasView.memoContainerStackView.topAnchor.constraint(equalTo: memoCanvasView.topAnchor).isActive = true
        memoCanvasView.memoContainerStackView.widthAnchor.constraint(equalTo: memoCanvasView.widthAnchor, multiplier: 0.75).isActive = true
        memoCanvasView.memoContainerStackView.heightAnchor.constraint(equalTo: memoCanvasView.heightAnchor).isActive = true
        
        for ( _ , tableViewController ) in memoTableViewControllers {
            tableViewController.memoContainerView.topAnchor.constraint(equalTo: memoCanvasView.memoContainerStackView.topAnchor).isActive = true
            tableViewController.memoContainerView.bottomAnchor.constraint(equalTo: memoCanvasView.memoContainerStackView.bottomAnchor).isActive = true
        }
    }
    
    func removeSelectedMemoModel(containerType: MemoStatus, indexPath: IndexPath) {
        memoManager.removeSelectedMemoModel(containerType: containerType, index: indexPath.section)
    }
    
    func insertSelectedMemoModel(containerType: MemoStatus, indexPath: IndexPath, memoModel: Memo) {
        memoManager.insertSelectedMemoModel(containerType: containerType, index: indexPath.section, memo: memoModel)
    }
    
    private func subscribeObserver() {
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(didMemoAdd(_:)),
                                               name: .MemoDidAdd,
                                               object: nil)
    }
    
    @objc func didMemoAdd(_ notification: Notification) {
        if let memo = notification.userInfo?[UserInfoKeys.memo] as? Memo {
            updateView(containerType: memo.status)
        }
    }
    
    private func updateView(containerType: MemoStatus) {
        memoTableViewControllers[containerType]?.memoContainerView.tableView.reloadData()
        
        guard let currentMemoCount = memoTableViewControllers[containerType]?.memoContainerView.tableView.numberOfSections else {
            return
        }
        memoTableViewControllers[containerType]?.memoContainerView.countLabel.text = "\(currentMemoCount)"
    }
}

