import Foundation

enum CardList: String, Codable {
    case todo
    case inProgress
    case done
    
    var titleName: String {
        switch self {
            case .todo:
                return "해야할 일"
            case .inProgress:
                return "하고있는 일"
            case .done:
                return "완료한 일"
        }
    }
    
    var id: Int {
        switch self {
            case .todo:
                return 1
            case .inProgress:
                return 2
            case .done:
                return 3
        }
    }
}
