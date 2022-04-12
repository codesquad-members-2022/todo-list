import UIKit

class FetchCardDataMiddleWare {
    
    private var dataTask = DebugDataTask.init(api: Team13API())
    private var cardsInBoard = [TodoBoard: [CardData]]()
    
    func fetchAllCards(_ completionHandler: @escaping (([CardData]?) -> Void)) {
        dataTask?.fetchAll(dataType: CardMap.self) { [weak self] (result: Result<CardMap, DataTaskError>) in
            
            guard let self = self else { return }
            
            switch result {
            case .success(let cardMap):
                
                completionHandler(cardMap.cardMap.todo)
                
            case .failure(let error):
                
                Log.error(error.localizedDescription)
                completionHandler(nil)
            }
        }
    }
}
