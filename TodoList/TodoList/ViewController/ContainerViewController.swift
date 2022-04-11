//
//  ViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/04.
//

import UIKit

final class ContainerViewController: UIViewController{
    var headerVC: HeaderViewController!
    var contentVC: ContentViewController!
    var tableVC: TableViewController!
    var menuVC: ActivityMenuViewController!
    let menuViewWidth: CGFloat = 428.0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.layer.backgroundColor = UIColor(red: 0.961, green: 0.961, blue: 0.969, alpha: 1).cgColor
        addChildViewController()
    }
}

private extension ContainerViewController{
    func addChildViewController(){
        headerVC = HeaderViewController()
        self.addChild(headerVC)
        self.view.addSubview(headerVC.view)
        headerVC.header.delegate = self
        
        contentVC = ContentViewController()
        contentVC.cellDelegate = self
        self.addChild(contentVC)
        self.view.addSubview(contentVC.view)
        
        tableVC = TableViewController()
        self.addChild(tableVC)
        
        menuVC = ActivityMenuViewController()
        self.addChild(menuVC)
        self.view.addSubview(menuVC.view)
        menuVC.activityView.delegate = self
        
        configureChildViewLayout()
    }
    
    func configureChildViewLayout(){
        let headerHeight: CGFloat = 72
        
        headerVC.view.translatesAutoresizingMaskIntoConstraints = false
        headerVC.view.leadingAnchor.constraint(equalTo: self.view.leadingAnchor).isActive = true
        headerVC.view.topAnchor.constraint(equalTo: self.view.topAnchor).isActive = true
        headerVC.view.widthAnchor.constraint(equalTo: self.view.widthAnchor).isActive = true
        headerVC.view.heightAnchor.constraint(equalToConstant: headerHeight).isActive = true
        
        contentVC.view.translatesAutoresizingMaskIntoConstraints = false
        contentVC.view.topAnchor.constraint(equalTo: headerVC.view.bottomAnchor).isActive = true
        contentVC.view.leadingAnchor.constraint(equalTo: self.view.leadingAnchor).isActive = true
        contentVC.view.bottomAnchor.constraint(equalTo: self.view.bottomAnchor).isActive = true
        contentVC.view.widthAnchor.constraint(equalTo: self.view.widthAnchor).isActive = true
        
        menuVC.view.translatesAutoresizingMaskIntoConstraints = false
        menuVC.view.leadingAnchor.constraint(equalTo: self.view.trailingAnchor).isActive = true
        menuVC.view.topAnchor.constraint(equalTo: self.view.topAnchor).isActive = true
        menuVC.view.bottomAnchor.constraint(equalTo: self.view.bottomAnchor).isActive = true
        menuVC.view.widthAnchor.constraint(equalToConstant: self.menuViewWidth).isActive = true
    }
}

extension ContainerViewController: TableViewInCollectionCell{
    func didSetTableView(cell: CollectionCell, index: Int) {
        self.tableVC.setTableAttributes(cell: cell, index: index)
    }
}


extension ContainerViewController: HeaderViewDelegate{
    func headerMenuButtonDidTouched() {
        UIView.animate(withDuration: 0.5) {
            self.menuVC.view.transform = CGAffineTransform(translationX: -(self.menuViewWidth), y: 0)
        }
    }
}

extension ContainerViewController: ActivityViewDelegate{
    func closeButtonDidTouched() {
        UIView.animate(withDuration: 0.5) {
            self.menuVC.view.transform = CGAffineTransform(translationX: self.menuViewWidth, y: 0)
        }
    }
}
