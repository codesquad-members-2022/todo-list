import UIKit

class MemoCanvasViewController: UIViewController {

    private var memoCanvasView: MemoCanvasView = {
        let view = MemoCanvasView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    // childVC의 viewDidLoad()라고 생각
    override func didMove(toParent parent: UIViewController?) {
        view = memoCanvasView
        memoCanvasView.todoContainerView.tableView.dataSource = self

    }
}

extension MemoCanvasViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 15
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: MemoTableViewCell.identifier, for: indexPath) as? MemoTableViewCell else {
            return UITableViewCell()
        }
        
        let memo = Memo(title: "해야 할 일", content: "해야 할 일 내용", name: "JK")
        cell.updateStackView(memo: memo)
        return cell
    }
    
}
