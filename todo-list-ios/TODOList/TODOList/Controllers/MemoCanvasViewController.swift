import UIKit

class MemoCanvasViewController: UIViewController {

    private var memoCanvasView: MemoCanvasView = {
        let view = MemoCanvasView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    private var memoTableViewControllers: [Identifier: MemoTableViewController] {
        return [
            .todo : MemoTableViewController(cellCount: 15, identifier: .todo)
            , .progress : MemoTableViewController(cellCount: 15, identifier: .progress)
            , .done : MemoTableViewController(cellCount: 15, identifier: .done)
        ]
    }
    
    override func didMove(toParent parent: UIViewController?) {
        view = memoCanvasView
        memoCanvasView.todoContainerView.tableView.dataSource = self
        memoCanvasView.todoContainerView.tableView.delegate = self
        memoCanvasView.progressContainerView.tableView.dataSource = self
        memoCanvasView.doneContainerView.tableView.dataSource = self
    }
}

extension MemoCanvasViewController: UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 15
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: MemoTableViewCell.identifier, for: indexPath) as? MemoTableViewCell else {
            return UITableViewCell()
        }
        
        let memo = Memo(title: "해야 할 일입니당", content: "해야할 일의 내용입니다\n할게 너무 많아요\n열심히 하세요", name: "JK")
        cell.updateStackView(memo: memo)
        cell.updateStyle()
        return cell
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

