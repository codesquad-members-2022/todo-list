//
//  ContentViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/05.
//

import UIKit

class ContentViewController: UIViewController {
    private var collectionView: CollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setTodoTableView()
        setCollectionViewDelegate()
        collectionView.register(CollectionCell.classForCoder(), forCellWithReuseIdentifier: "collectionCell")
    }
    
    func setTodoTableView(){
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        
        collectionView = CollectionView(frame: CGRect(x: 0, y: 0, width: view.frame.width, height: (view.frame.height)*(5/6)), collectionViewLayout: layout)
        
        self.view.addSubview(collectionView)
        
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        collectionView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 15).isActive = true
        collectionView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor).isActive = true
        collectionView.topAnchor.constraint(equalTo: self.view.topAnchor).isActive = true
        collectionView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor).isActive = true
    }
    
    func setCollectionViewDelegate(){
        collectionView.delegate = self
        collectionView.dataSource = self
    }
}

extension ContentViewController: UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout{
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 3
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "collectionCell", for: indexPath) as? CollectionCell else { return UICollectionViewCell() }
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        // 3등분해서 배치하고 옆 간격인 10을 빼주기
        let size = CGSize(width: self.view.frame.width / 3 - 25, height: self.view.frame.height)
        return size
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 30)
    }
}
