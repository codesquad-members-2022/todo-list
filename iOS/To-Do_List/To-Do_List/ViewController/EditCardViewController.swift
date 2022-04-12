//
//  EditCardViewController.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/12.
//

import UIKit

class EditCardViewController: UIViewController {

    private var CardCreationView : EditCardView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .blue
        self.preferredContentSize = CGSize(width: 400, height: 175)
    }
       

}
