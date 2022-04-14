//
//  BoardTableDelegate.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/13.
//

protocol BoardTableViewDelegate {
    func didTapDelete(item: Any)
    func didTapMoveToCompleted(foucsedCard:Todo,from:Int)
}
