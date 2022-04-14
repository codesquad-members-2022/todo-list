//
//  AddCardDelegate.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/13.
//

import Foundation

protocol AddCardDelegate: AnyObject{
    func makeCardShoudCanceld()
    func makeCardShoudConfirmed(title: String, content: String)
}
