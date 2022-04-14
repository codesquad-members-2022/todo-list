import UIKit

class ActionBoardViewController: UIViewController {
    
    @IBOutlet private weak var table: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupCell()
    }
    
    private func setupCell() {
        let nibName = UINib(nibName: nibTitle.actionCardViewCell, bundle: nil)
        table.delegate = self
        table.dataSource = self
        table.register(nibName, forCellReuseIdentifier: identifier.actionCardViewCell)
    }

    @IBAction func closeButtonTapped(_ sender: UIButton) {
        NotificationCenter.default.post(name: .actionFlowCloseButtonTapped, object: nil)
    }
}

extension ActionBoardViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 4
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: identifier.actionCardViewCell, for: indexPath) as? ActionCardViewCell else { return UITableViewCell() }
        return cell
    }

}
