//
//  CollectionView.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/05.
//

import UIKit

class CollectionView: UICollectionView {
    override init(frame: CGRect, collectionViewLayout layout: UICollectionViewLayout) {
        super.init(frame: frame, collectionViewLayout: layout)
        self.backgroundColor = .systemGray5
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.backgroundColor = .systemGray5
    }
}
