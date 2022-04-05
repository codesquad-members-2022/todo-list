import UIKit

class ViewController: UIViewController {
    
    private let sideView: SideView = {
        let view = SideView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setLayout()
    }
    
    private func setLayout(){
        self.view.addSubview(sideView)
        NSLayoutConstraint.activate([
            sideView.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 0),
            sideView.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor, constant: 0),
            sideView.widthAnchor.constraint(equalToConstant: 200),
            sideView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor, constant: 0)
        ])
    }
    
}

