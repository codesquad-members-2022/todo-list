import UIKit

protocol TodoEndPointViewController {
    var middleWare: CardDataMiddleWare { get }
    var boardType: TodoBoard { get }
}

class ViewController: UIViewController {
    private var dataManager: PersistenceProvider?
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        AppDelegate.middleWare.fetchAllCards()
    }
    
    func setDataManager(_ manager: PersistenceProvider) {
        self.dataManager = manager
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
