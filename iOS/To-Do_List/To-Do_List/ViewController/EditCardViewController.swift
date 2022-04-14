//
//  EditCardViewController.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/12.
//

import UIKit

final class EditCardViewController: UIViewController {

    var delegate:EditViewControllerDelegate?
    
    private var editCardView : EditCardView = EditCardView(frame: .zero)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setup()
    }
       
    private func setup() {
        self.editCardView.delegate = self
        
        let editViewSize = CGSize(width: 400, height: 175)
        self.editCardView.backgroundColor = .systemBackground
        self.preferredContentSize = CGSize(width: editViewSize.width , height: editViewSize.height)
        
        self.view.addSubview(editCardView)
        
        editCardView.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            editCardView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            editCardView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            editCardView.topAnchor.constraint(equalTo: self.view.topAnchor),
            editCardView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor)
        ])
    }
    
    func setEditCardView(editStyle:EditStyle) {
        self.editCardView.setEditCardView(editStyle: editStyle)
    }
    
}

//MARK: -- EditCardView Delegation
extension EditCardViewController : EditCardViewDelegate {
    func didTapConfirmButton(editViewInfo: EditViewInputInfo) {
        delegate?.didTapConfirmButton(editViewInfo: editViewInfo)
        self.dismiss(animated: true, completion: nil)
    }
    
    func didTapCancelButton() {
        self.dismiss(animated: true, completion: nil)
    }
    
    func textFieldDidEndEditing() {
        editCardView.changeConfirmButtonState()
    }
}
