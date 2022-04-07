import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var cardListStackView: UIStackView!
    
    private let todoViewController = CardListViewController(title: "해야할 일")
    private let inProgressViewController = CardListViewController(title: "진행중인 일")
    private let doneViewController = CardListViewController(title: "완료한 일")
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
       
        self.addChild(todoViewController)
        self.addChild(inProgressViewController)
        self.addChild(doneViewController)
        
        layout()
    }
    
    private func layout() {
        self.cardListStackView.clipsToBounds = true
        self.children.forEach {
            self.cardListStackView.addArrangedSubview($0.view)
        }
    }
}
