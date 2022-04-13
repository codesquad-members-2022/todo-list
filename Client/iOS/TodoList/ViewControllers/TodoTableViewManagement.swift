import UIKit

class FetchCardDataMiddleWare: NSObject {
    private(set) var targetTableView: UITableView?
    private var dataTask = DebugDataTask.init(api: Team13API())

    lazy var dataSource = [CardData]() {
        didSet {
            DispatchQueue.main.async { [weak self] in
                guard let self = self, let tableView = self.targetTableView else { return }
                tableView.reloadData()
            }
        }
    }
    
    func setTableView(_ tableView: UITableView) {
        targetTableView = tableView
    }
    
    func fetchAllCards() {
        dataTask?.fetchAll(dataType: CardMap.self)
        { [weak self] (result: Result<CardMap, DataTaskError>) in
            
            guard let self = self else { return }
            
            switch result {
                
            case .success(let cardMap):
                self.dataSource = cardMap.cardMap.todo
                
            case .failure(let error):
                Log.error(error.localizedDescription)
            }
        }
    }
    
    func getCards(in board: TodoBoard) -> [CardData]? {
        return dataSource.filter({ card in card.boardName == board.rawValue})
    }
}

extension FetchCardDataMiddleWare: UITableViewDataSource {

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        dataSource.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: TodoTableViewCell.cellName, for: indexPath) as? TodoTableViewCell else {
            return UITableViewCell()
        }

        cell.reloadAllLabels(dataSource[indexPath.row])

        return cell
    }
}

extension FetchCardDataMiddleWare: UITableViewDelegate { }
