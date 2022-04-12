import UIKit

class MainViewController: UIViewController {
    
    enum mainConstant {
        static let serviceName = "TO-DO LIST - team10"
    }
    
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var cardListStackView: UIStackView!
    
    private let todoViewController = CardListViewController(type: .todo, cardManager: CardManager(cardListType: .todo, cardFactory: ModelFactory()))
    private let inProgressViewController = CardListViewController(type: .inProgress, cardManager: CardManager(cardListType: .inProgress, cardFactory: ModelFactory()))
    private let doneViewController = CardListViewController(type: .done, cardManager: CardManager(cardListType: .done, cardFactory: ModelFactory()))
    
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
