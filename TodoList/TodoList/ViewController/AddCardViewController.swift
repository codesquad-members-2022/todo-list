//
//  AddCardViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/13.
//

import UIKit

final class AddCardViewController: UIViewController {
    var addCardView: AddCardView!
    var board: [Card]?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .clear
        setaddCardView()
        addCardView.delegate = self
    }
}

private extension AddCardViewController{
    func setaddCardView(){
        let xCoord = self.view.bounds.width / 2 - 200
        let yCoord = self.view.bounds.height / 2 - 87

        addCardView = AddCardView(frame: CGRect(x: xCoord, y: yCoord, width: 400, height: 175))
        addCardView.setCardText(title: nil, body: nil)
        self.view.addSubview(addCardView)
    }
}

extension AddCardViewController: AddCardDelegate{
    func makeCardShoudConfirmed(title: String, content: String) {
        
        self.dismiss(animated: true, completion: nil)
    }
    
    func makeCardShoudCanceld() {
        self.dismiss(animated: true, completion: nil)
    }

    
    
}
