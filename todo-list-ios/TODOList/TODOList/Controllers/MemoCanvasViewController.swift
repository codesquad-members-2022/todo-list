import UIKit

class MemoCanvasViewController: UIViewController {
    
    private var memoCanvasView: MemoCanvasView = {
        let view = MemoCanvasView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    private var memoTableViewControllers: [Identifier: MemoTableViewController] = [:]
    
    override func didMove(toParent parent: UIViewController?) {
        view = memoCanvasView
        memoCanvasView.todoContainerView.tableView.dataSource = self
        memoCanvasView.todoContainerView.tableView.delegate = self
        memoCanvasView.progressContainerView.tableView.dataSource = self
        memoCanvasView.doneContainerView.tableView.dataSource = self
        
        addTableViewControllers()
        setLayout()
    }
    
    private func addTableViewControllers() {
        for identifier in Identifier.allCases {
            let tableViewController = MemoTableViewController(cellCount: 15, identifier: identifier)
            memoTableViewControllers[identifier] = tableViewController
            
            addChild(tableViewController)
            memoCanvasView.memoContainerStackView.addArrangedSubview(tableViewController.view)
            tableViewController.didMove(toParent: self)
            
            addTableViewConfigurations(tableViewController: tableViewController)
        }
    }
    
    private func addTableViewConfigurations(tableViewController: MemoTableViewController) {
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


extension MemoCanvasViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    
    func tableView(_ tableView: UITableView, editingStyleForRowAt indexPath: IndexPath) -> UITableViewCell.EditingStyle {
        return .delete
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
        }
    }
    
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let vc = PopupViewController()

        switch indexPath.section {
        case 0:
            self.present(vc, animated: true)
        default:
            self.present(vc, animated: true)
        }
    }
}

