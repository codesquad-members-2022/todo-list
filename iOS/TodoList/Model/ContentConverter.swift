import Foundation

struct ContentConverter {
    private var event: RequestEventData
    init(event: RequestEventData) {
        self.event = event
    }
    
    enum Event {
        case move
        case add
        case edit
        case delete
    }
    
    var action: Event {
        switch self.event.action {
        case "ADD":
            return .add
        case "REMOVE":
            return .delete
        case "MOVE":
            return .move
        case "UPDATE":
            return .edit
        default:
            return .add
        }
    }
    
    var image: String {
        switch self.action {
        case .add:
            return "👍"
        case .edit:
            return "🛠"
        case .move:
            return "🚗"
        case .delete:
            return "🫥"
        }
    }
    
    var content: String {
        switch self.action {
        case .move:
            return "\(event.cardTitle)를 \(event.prevSection)에서 \(event.currentSection)로 이동하였습니다."
        case .add:
            return "\(event.currentSection)에 \(event.cardTitle)를 등록하였습니다."
        case .edit:
            return "\(event.cardTitle)를 수정하였습니다."
        case .delete:
            return "\(event.cardTitle)를 삭제하였습니다."
        }
    }
    
    var time: String {
        let timeString = self.event.createdAt.replacingOccurrences(of: "T", with: " ")
        guard let actionTime = timeString.toDate() else { return "" }
        let now = Date()
        let time = Int(now.timeIntervalSince(actionTime))/60
        return time == 0 ? "방금" : "\(time)분전"
    }
}

