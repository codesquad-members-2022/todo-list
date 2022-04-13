import UIKit

class CardDataMiddleWare {
    
    private var dataTask = DebugDataTask.init(api: Team13API())
    private var fetchResultInBoard = [TodoBoard: Result<[CardData], DataTaskError>]() {
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
    
    func fetchAllCards() {
        dataTask?.fetchAll(dataType: CardMap.self) { [weak self] (result: Result<CardMap, DataTaskError>) in
            
            guard let self = self else { return }
            
            for board in TodoBoard.allCases {
                
                switch result {
                    
                case .success(let cardMap):
                    
                    self.fetchResultInBoard[board] = Result.success(cardMap.cardMap.todo)
                    
                case .failure(let error):
                    
                    Log.error(error)
                }
            }
        }
    }
    
    func getCards(in board: TodoBoard) -> Result<[CardData], DataTaskError> {
        guard fetchResultInBoard.keys.contains(board), let result = fetchResultInBoard[board] else {
            return Result.failure(DataTaskError.notConnect)
        }
        
        return result
    }
}
