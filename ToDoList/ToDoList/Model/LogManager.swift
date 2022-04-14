import Foundation

class LogManager {
    private var logs = [Log]()
    
    var count: Int {
        return logs.count
    }
    
    subscript(_ index: Int) -> Log {
        return logs[index]
    }
    
    func add() {
        let newLog = LogFactory.makeRandomLog()
        logs.append(newLog)
    }
}
