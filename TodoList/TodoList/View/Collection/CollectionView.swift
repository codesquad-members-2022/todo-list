//
//  CollectionView.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/05.
//

import UIKit

final class CollectionView: UICollectionView {
    override init(frame: CGRect, collectionViewLayout layout: UICollectionViewLayout) {
        super.init(frame: frame, collectionViewLayout: layout)
        self.layer.backgroundColor = UIColor(red: 0.961, green: 0.961, blue: 0.969, alpha: 1).cgColor
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.layer.backgroundColor = UIColor(red: 0.961, green: 0.961, blue: 0.969, alpha: 1).cgColor
    }
}
