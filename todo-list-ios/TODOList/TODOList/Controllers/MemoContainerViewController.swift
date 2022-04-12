import UIKit

class MemoContainerViewController: UIViewController {
    
    private var cellCount: Int?
    private var containerType: MemoContainerType?
    
    private (set) var memoContainerView: MemoContainerView = {
        let containerView = MemoContainerView()
        containerView.translatesAutoresizingMaskIntoConstraints = false
        return containerView
    }()
    
    convenience init(cellCount: Int, containerType: MemoContainerType){
        self.init()
        self.cellCount = cellCount
        self.containerType = containerType
        memoContainerView.categoryLabel.text = " \(containerType)"
        view = memoContainerView
    }
}

extension MemoContainerViewController: UITableViewDataSource & UITableViewDelegate {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        guard let cellCount = cellCount else { return 0 }
        return cellCount
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
