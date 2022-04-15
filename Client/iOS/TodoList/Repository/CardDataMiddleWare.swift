import UIKit

class CardDataMiddleWare {
    
    private var fetchResultInBoard = [TodoBoard: Result<[Card], DataTaskError>]() {
        willSet {
            for board in newValue.keys {
                guard let result = newValue[board] else { continue }
                NotificationCenter
                    .default
                    .post(
                        name: board.notificationName,
                        object: self,
                        userInfo: ["result":result]
                    )
            }
        }
    }
    private var dataTask: APIService?
    private let persistenceProvider: PersistenceProvider?
    
    init(persistenceProvider: PersistenceProvider, dataTaskGenerator: ()-> APIService?) {
        self.persistenceProvider = persistenceProvider
        self.dataTask = dataTaskGenerator()
    }
    
    func fetchAllCards() {
        guard let resultFromCoredata = persistenceProvider?.fetchAllCard(where: .all) else {
            return
        }
        
        dataTask?.fetchAll(dataType: CardMap.self) { [weak self] (result: Result<CardMap, DataTaskError>) in
            guard let self = self else { return }

            for board in TodoBoard.allCases {
                switch result {
                case .success(let cardMap):
                    let todos = cardMap.cardMap.TODO.map{ Card(id: $0.id, title: $0.title, contents: $0.contents, boardName: $0.boardName) }
                    // TODO:- board의 이름에 따라 type값이 달라져야 한다
                    self.persistenceProvider?.insertBoard(type: .todo, cards: todos)
                    self.fetchResultInBoard[board] = Result.success(todos)
                    
                case .failure(let error):
                    Log.error(error)
                    self.fetchResultInBoard[board] = Result.success(resultFromCoredata)
                }
            }
        }
    }
    
    func getCards(in board: TodoBoard) -> Result<[Card], DataTaskError> {
        guard fetchResultInBoard.keys.contains(board), let result = fetchResultInBoard[board] else {
            return Result.failure(DataTaskError.notConnect)
        }
        return result
    }
}
