//
//  TableHeaderDelegate.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/13.
//

import Foundation

protocol TableHeaderDelegate: AnyObject{
    func cardWillCreated(at section: Int)
}
