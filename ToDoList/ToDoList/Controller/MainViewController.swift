import UIKit

class MainViewController: UIViewController {
    
    enum mainConstant {
        static let serviceName = "TO-DO LIST - team10"
    }
    
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var cardListStackView: UIStackView!
    
    private let todoViewController = CardListViewController(title: "해야할 일", cardManager: CardManager(listName: "해야할 일", cardFactory: ModelFactory()))
    private let inProgressViewController = CardListViewController(title: "진행중인 일", cardManager: CardManager(listName: "진행중인 일", cardFactory: ModelFactory()))
    private let doneViewController = CardListViewController(title: "완료한 일", cardManager: CardManager(listName: "완료한 일", cardFactory: ModelFactory()))
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.titleLabel.text = mainConstant.serviceName
        
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
