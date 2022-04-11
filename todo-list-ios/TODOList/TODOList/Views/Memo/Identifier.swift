import Foundation

enum Identifier: String, CaseIterable, CustomStringConvertible {
    case todo = "해야 할 일"
    case progress = "하고있는 일"
    case done = "완료한 일"
    
    var description: String {
        return self.rawValue
    }
}
