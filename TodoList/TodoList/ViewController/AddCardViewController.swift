//
//  AddCardViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/13.
//

import UIKit

final class AddCardViewController: UIViewController {
    var addCardView: AddCardView!
    var sectionNumber: Int?
    var isPatch: Bool?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .clear
    }
    
    func configureCardView(){
        let xCoord = self.view.bounds.width / 2 - 200
        let yCoord = self.view.bounds.height / 2 - 87

        addCardView = AddCardView(frame: CGRect(x: xCoord, y: yCoord, width: 400, height: 175))
        
        addCardView.delegate = self
        self.view.addSubview(addCardView)
    }
    
    func setaddCardView(title: String?, body: String?){
        configureCardView()
        
        if let title = title, let body = body {
            addCardView.setCardText(title: title, body: body)
            self.isPatch = true
        } else{
            addCardView.setCardText(title: nil, body: nil)
            self.isPatch = false
        }
    }
}

extension AddCardViewController: AddCardDelegate{
    func makeCardShoudConfirmed(title: String, content: String) {
        guard let number = self.sectionNumber, let section = BoardSubscriptIndex(rawValue: number) else { return }
        let newCard = Card(section: number, title: title, content: content, userID: "puco")
  
        Board.shared.postCard(card: newCard, section: section)
        
        // POST
        self.dismiss(animated: true, completion: {
            self.addCardView.clear()
        })
    }
    
    func makeCardShoudCanceld() {
        self.dismiss(animated: true, completion: {
            self.addCardView.clear()
        })
    }

    
    
}
