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
        self.layer.backgroundColor = ColorMaker.lightGray0.getRawValue().cgColor
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.layer.backgroundColor = ColorMaker.lightGray0.getRawValue().cgColor
    }
}
