import UIKit

class MemoCanvasViewController: UIViewController {
    
    private var memoCanvasView: MemoCanvasView = {
        let view = MemoCanvasView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    private (set) var memoTableViewControllers: [MemoContainerType: MemoContainerViewController] = [:]
    private (set) var memoTableViewModels: [MemoContainerType: [Memo]] = [.todo:[], .progress:[], .done:[]]
    
    override func didMove(toParent parent: UIViewController?) {
        view = memoCanvasView
        
        initProperties()
        setLayout()
    }
    
    private func initProperties() {
        for containerType in MemoContainerType.allCases {
            for _ in 0..<15 {
                addTableViewModel(containerType: containerType)
            }
            addTableViewController(containerType: containerType)
        }
    }
    
    private func addTableViewModel(containerType: MemoContainerType) {
        let memo = Memo(title: "해야 할 일입니당", content: "해야할 일의 내용입니다\n할게 너무 많아요\n열심히 하세요", name: "JK")
        memoTableViewModels[containerType]?.append(memo)
    }
    
    private func addTableViewController(containerType: MemoContainerType) {
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
}

