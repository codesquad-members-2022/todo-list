import Foundation

enum NetworkTarget {
    case loadCardList(cardListId: Int) 
    case createCard(title: String, body: String, cardListId: Int)
    case deleteCard(cardId: Int)
    case editCard(cardId: Int, title: String, body: String)
    case moveCard(cardId: Int, targetCardListId: Int, targetCardId: Int)
}

extension NetworkTarget: NetworkTargetable {
    var path: String {
        switch self {
            case .loadCardList(let cardListId):
                return "/cards/section/\(cardListId)"
            case .createCard:
                return "/cards"
            case .deleteCard(let cardId), .editCard(let cardId, _, _):
                return "/cards/\(cardId)"
            case .moveCard(let cardId, _, _):
                return "/cards/move/\(cardId)"
        }
    }
    
    var parameter: [String : Any]? {
        switch self {
            case .loadCardList(_):
                return nil
            case .createCard(let title, let body, let cardListId):
                return ["author": "iOS", "contents": body, "sectionId": cardListId, "subject": title]
            case .deleteCard(_):
                return nil
            case .editCard(_, let title, let body):
                return ["contents": body, "subject": title]
            case .moveCard(_, let targetCardListId, let targetCardId):
                return ["sectionId": targetCardListId, "targetCardId": targetCardId]
        }
    }
    
    var method: String {
        switch self {
            case .loadCardList(_):
                return "GET"
            case .createCard(_,_,_):
                return "POST"
            case .deleteCard(_):
                return "DELETE"
            case .editCard(_,_,_), .moveCard(_,_,_):
                return "PATCH"
        }
    }
}
