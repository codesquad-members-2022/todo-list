import UIKit

class MemoCanvasViewController: UIViewController {

    private var memoCanvasView: MemoCanvasView = {
        let view = MemoCanvasView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    // childVC의 viewDidLoad()라고 생각
    override func didMove(toParent parent: UIViewController?) {
        view = memoCanvasView
    }
}
