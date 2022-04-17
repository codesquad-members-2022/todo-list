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
    private var alert: UIAlertController!
    
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
        configureAlert()
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
    
    func configureAlert(){
        alert = UIAlertController(title: "등록할 수 없습니다.", message: "제목과 내용을 모두 입력해주세요", preferredStyle: .alert)
        let alertAction = UIAlertAction(title: "확인", style: .default, handler: nil)
        alert.addAction(alertAction)
    }
}

extension AddCardViewController: AddCardDelegate{
    func makeCardShoudConfirmed(title: String, content: String) {
        guard let section = self.sectionIndex else { return }
        
        if let selectedCard = self.selectedCard{
            Board.shared.patchCard(card: selectedCard, section: section)
        } else{
            let newCard = Card(section: section.rawValue, title: title, content: content, userID: "chez")
            Board.shared.postCard(card: newCard, section: section)
        }
        
        self.selectedCard = nil
        
        // POST
        self.dismiss(animated: true, completion: {
            self.addCardView.clear()
        })
    }
    
    func makeCardShoudCanceld() {
        self.selectedCard = nil
        self.dismiss(animated: true, completion: {
            self.addCardView.clear()
        })
    }
    
    func cardshoudNotMake(){
        self.present(alert, animated: true, completion: nil)
    }
}
