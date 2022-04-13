import UIKit

class MainViewController: UIViewController {
    
    static let serviceName = "TO-DO LIST - team10"
    
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var cardListStackView: UIStackView!
    
    private let todoViewController = CardListViewController(type: .todo, cardManager: CardManager(cardListType: .todo, cardFactory: ModelFactory()))
    private let inProgressViewController = CardListViewController(type: .inProgress, cardManager: CardManager(cardListType: .inProgress, cardFactory: ModelFactory()))
    private let doneViewController = CardListViewController(type: .done, cardManager: CardManager(cardListType: .done, cardFactory: ModelFactory()))
    
    var logViewController: LogListViewController? = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.titleLabel.text = MainViewController.serviceName

        self.logViewController = LogListViewController(coder: self)
        
        self.addChild(todoViewController)
        self.addChild(inProgressViewController)
        self.addChild(doneViewController)
        self.addChild(logViewController!)
        layout()
    }
    
    private func layout() {
        self.cardListStackView.clipsToBounds = true
        for stackViewChildIndex in 0...2 {
            self.cardListStackView.addArrangedSubview(children[stackViewChildIndex].view)
        }
        
        self.logViewController!.view.isHidden = true
        
        self.view.addSubview(children[3].view)
        children[3].view.translatesAutoresizingMaskIntoConstraints = false
        
        children[3].view.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        children[3].view.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
        children[3].view.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        children[3].view.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 766).isActive = true
        
    }
    
    @IBAction func didClickOpenButton(_ sender: UIButton) {
        UIView.animate(withDuration: 0.5) {
            self.logViewController!.view.isHidden = self.logViewController!.view.isHidden == true ? false : true
        }
    }
}
