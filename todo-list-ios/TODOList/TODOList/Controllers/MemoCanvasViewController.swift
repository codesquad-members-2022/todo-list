import UIKit

class MemoCanvasViewController: UIViewController {
    
    private var memoCanvasView: MemoCanvasView = {
        let view = MemoCanvasView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    private var memoTableViewControllers: [MemoContainerType: MemoContainerViewController] = [:]
    
    override func didMove(toParent parent: UIViewController?) {
        view = memoCanvasView

        addTableViewControllers()
        setLayout()
    }
    
    private func addTableViewControllers() {
        for containerType in MemoContainerType.allCases {
            let tableViewController = MemoContainerViewController(cellCount: 15, containerType: containerType)
            memoTableViewControllers[containerType] = tableViewController
            
            addChild(tableViewController)
            memoCanvasView.memoContainerStackView.addArrangedSubview(tableViewController.view)
            tableViewController.didMove(toParent: self)
            
            addTableViewConfigurations(tableViewController: tableViewController)
        }
    }
    
    private func addTableViewConfigurations(tableViewController: MemoContainerViewController) {
        tableViewController.memoContainerView.tableView.dataSource = tableViewController
        tableViewController.memoContainerView.tableView.dataSource = tableViewController
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

