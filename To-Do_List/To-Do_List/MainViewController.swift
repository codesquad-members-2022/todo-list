//
//  MainViewController.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/04.
//

import UIKit

class MainViewController: UIViewController {

//    @IBOutlet weak var tableStackView: UIStackView!
    
    @IBOutlet weak var statckView: UIStackView!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        self.title = "TO-DO-LIST"
        self.navigationController?.navigationBar.backgroundColor = .darkGray
        
        // 현재의 뷰 컨트롤러에 자식 뷰 컨트롤러를 추가하는 코드
        let storyboard1 = UIStoryboard(name: "Main", bundle: .main)
        if let viewController = storyboard1.instantiateViewController(identifier: "ToDoTableViewController")
                                            as? ToDoTableViewController {
            
           // 핵심 메소드로서, 뷰 컨트롤러에 부모-자식 관계를 형성한다
           addChild(viewController)
           
           // 자식 뷰 컨트롤러의 루트 뷰를 현재 뷰의 뷰 계층에 추가하는 코드
            self.statckView.addArrangedSubview(viewController.view)
        }
        
        let storyboard2 = UIStoryboard(name: "Main", bundle: .main)
        if let viewController = storyboard2.instantiateViewController(identifier: "DoneTableViewController")
                                            as? DoneTableViewController {

           // 핵심 메소드로서, 뷰 컨트롤러에 부모-자식 관계를 형성한다
           addChild(viewController)

            self.statckView.addArrangedSubview(viewController.view)
        }
        
        let storyboard3 = UIStoryboard(name: "Main", bundle: .main)
        if let viewController = storyboard3.instantiateViewController(identifier: "DoingTableViewController")
                                            as? DoingTableViewController {

           // 핵심 메소드로서, 뷰 컨트롤러에 부모-자식 관계를 형성한다
           addChild(viewController)

            self.statckView.addArrangedSubview(viewController.view)
        }
    }
    
}
