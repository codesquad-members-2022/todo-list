import UIKit

protocol TodoCardViewMiddleWare {
    func requestCards(in board: TodoBoard, _ completionHandler: @escaping ([CardData]?)->Void)
}

protocol TodoEndPointViewController {
    var middleWare: TodoCardViewMiddleWare? { get set }
    var boardType: TodoBoard { get }
}

class ViewController: UIViewController {
    let cardMiddleWare = FetchCardDataMiddleWare()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if var dest = segue.destination as? TodoEndPointViewController {
            dest.middleWare = self
        }
    }
}

extension ViewController: TodoCardViewMiddleWare {
    
    ///
    ///
    /// - in: 어떤 테이블 뷰에서 전달하였는지 식별하는 부분입니다. 현재는 사용되지 않고 있습니다.
    func requestCards(in board: TodoBoard, _ completionHandler: @escaping ([CardData]?)->Void) {
        cardMiddleWare.fetchAllCards { data in
            completionHandler(data?.shuffled())
        }
    }
}

enum TodoBoard: String, CaseIterable {
    case todo = "todo"
    case progress = "progress"
    case completed = "completed"
}
