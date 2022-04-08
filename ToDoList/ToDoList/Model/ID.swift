import Foundation

struct ID {
    var description: String
    
    init?(with id: String) {
        if ID.isValidFormat(id) {
            self.description = id
        } else {
            return nil
        }
    }
    
    init() {
        self.description = ID.random()
    }
    
    static func random() -> String {
        let randomIDParts = [String(NSUUID().uuidString.prefix(3)),
                             String(NSUUID().uuidString.prefix(3)),
                             String(NSUUID().uuidString.prefix(3))]
        
        return randomIDParts.joined(separator: "-")
    }
    
    private static func isValidFormat(_ id: String) -> Bool {
        let correctFormat = "^[a-zA-Z0-9]..[-][a-zA-Z0-9]..[-][a-zA-Z0-9].."
        if let _ = id.range(of: correctFormat, options: .regularExpression) {
            return true
        } else {
            return false
        }
    }
}
