import UIKit

class ActionBoardViewController: UIViewController {
    
    @IBOutlet private weak var table: UITableView!
    
    private var events : [RequestEventData] = []
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        observe()
        setupCell()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.table.reloadData()
    }
    private func observe() {
        NotificationCenter.default.addObserver(forName: .getEventsData, object: nil, queue: .main, using: {notification in
            guard let events =  notification.userInfo?[NotificationKeyValue.getEventsData] as? [RequestEventData] else { return }
            self.events = events.reversed()
            DispatchQueue.main.async {
                self.table.reloadData()                
            }
        })
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
        return events.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: identifier.actionCardViewCell, for: indexPath) as? ActionCardViewCell else { return UITableViewCell() }
        let event = events[indexPath.row]
        let content = ContentConverter(event: event)
        cell.setData(image: content.image, content: content.content, timeStamp: content.time)
        return cell
    }

}
