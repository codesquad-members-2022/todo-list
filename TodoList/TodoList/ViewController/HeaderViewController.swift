//
//  HeaderViewController.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/05.
//

import UIKit

final class HeaderViewController: UIViewController{
    var header: HeaderView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setHeaderView()
    }
    
    func setHeaderView(){
        header = HeaderView()
        self.view = header
    }
}
