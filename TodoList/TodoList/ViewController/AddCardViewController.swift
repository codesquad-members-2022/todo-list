//
//  AddCardViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/13.
//

import UIKit

final class AddCardViewController: UIViewController {
    private var addCardView: AddCardView!
    private var sectionIndex: BoardSubscriptIndex?
    private var selectedCard: Card?
    
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
    
    func setaddCardView(sectionNumber: Int){
        configureCardView()
        self.sectionIndex = BoardSubscriptIndex(rawValue: sectionNumber) ?? BoardSubscriptIndex.none
        addCardView.setCardText(title: nil, body: nil)
    }
    
    func patchCardView(card: Card, section: BoardSubscriptIndex){
        configureCardView()
        self.sectionIndex = section
        self.selectedCard = card
        addCardView.setCardText(title: card.title, body: card.content)
    }
}

extension AddCardViewController: AddCardDelegate{
    func makeCardShoudConfirmed(title: String, content: String) {
        guard let section = self.sectionIndex else { return }
        
        if let selectedCard = self.selectedCard{
            Board.shared.patchCard(card: selectedCard, section: section)
        } else{
            let newCard = Card(section: section.rawValue, title: title, content: content, userID: "puco")
            Board.shared.postCard(card: newCard, section: section)
        }
        
        self.selectedCard = nil
        
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
