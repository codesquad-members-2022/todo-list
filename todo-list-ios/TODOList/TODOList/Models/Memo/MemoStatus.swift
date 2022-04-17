import Foundation

enum MemoStatus: Int, CaseIterable, CustomStringConvertible, Codable {
    case todo = 1
    case progress = 2
    case done = 3
    
    var description: String {
        switch self {
        case .todo:
            return "해야 할 일"
        case .progress:
            return "하고있는 일"
        case .done:
            return "완료한 일"
        }
    }

}
