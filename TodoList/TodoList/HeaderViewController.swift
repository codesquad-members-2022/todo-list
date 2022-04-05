//
//  HeaderViewController.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/05.
//

import Foundation
import UIKit

class HeaderViewController: UIViewController{
    override func viewDidLoad() {
        super.viewDidLoad()
        setHeaderView()
    }
    
    func setHeaderView(){
        let header = HeaderView(frame: CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height / 6))
        self.view = header
    }
}
