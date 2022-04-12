//
//  ContentViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/05.
//

import UIKit

final class ContentViewController: UIViewController {
    enum CellIdentifier: String{
        case collectionCell = "collectionCell"
        
        func getRawValue() -> String{
            return self.rawValue
        }
    }
    
    private var tableVC: TableViewController!
    private var collectionView: CollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.tableVC = TableViewController()
        setCollectionView()
        setCollectionViewDelegate()
    }
}

private extension ContentViewController{
    func setCollectionView(){
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        
        collectionView = CollectionView(frame: CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height), collectionViewLayout: layout)
        
        self.view.addSubview(collectionView)
        
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        collectionView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 48).isActive = true
        collectionView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor).isActive = true
        collectionView.topAnchor.constraint(equalTo: self.view.topAnchor).isActive = true
        collectionView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor).isActive = true
    }
    
    func setCollectionViewDelegate(){
        collectionView.delegate = self
        collectionView.dataSource = self
        collectionView.register(CollectionCell.classForCoder(), forCellWithReuseIdentifier: CellIdentifier.collectionCell.getRawValue())
    }
}

extension ContentViewController: UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout{
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 3
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: CellIdentifier.collectionCell.getRawValue(), for: indexPath) as? CollectionCell else { return UICollectionViewCell() }
        
        tableVC.setTableAttributes(cell: cell, index: indexPath.row)
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width: CGFloat = 258
        let size = CGSize(width: width, height: self.view.frame.height)
        return size
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 16)
    }
}
