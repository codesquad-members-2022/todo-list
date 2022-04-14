//
//  BoardTableDelegate.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/13.
//

protocol BoardTableViewDelegate {
    func DidTapDelete(item: Any)
    func DidTapMoveToCompleted(foucsedCard:Todo,from:Int)
}
