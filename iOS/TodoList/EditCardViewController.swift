//
//  EditCardViewController.swift
//  TodoList
//
//  Created by dale on 2022/04/06.
//

import UIKit

class EditCardViewController: UIViewController {
    @IBOutlet weak var centerView: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        guard let editCardView = Bundle.main.loadNibNamed("EditCardView", owner: nil, options: nil)?.first as? EditCardView else {return}
        self.centerView.addSubview(editCardView)
    }



}
