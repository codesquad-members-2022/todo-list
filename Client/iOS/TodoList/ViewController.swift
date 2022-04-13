import UIKit

protocol TodoEndPointViewController {
    var middleWare: CardDataMiddleWare { get }
    var boardType: TodoBoard { get }
}

class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        AppDelegate.middleWare.fetchAllCards()
    }
}

enum TodoBoard: String, CaseIterable {
    case todo = "todo"
    case progress = "progress"
    case completed = "completed"
    
    var notificationName: Notification.Name {
        Notification.Name(self.rawValue)
    }
}
