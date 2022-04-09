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
        cell.clipsToBounds = true
        cell.layer.cornerRadius = 15
        return cell
    }
    
}

