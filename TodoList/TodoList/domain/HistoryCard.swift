//
//  HistoryCard.swift
//  TodoList
//
//  Created by Bibi on 2022/04/12.
//

import Foundation

struct HistoryCard {
    let writer: String
    let title: String
    let beforeStatus: CardStatus
    let afterStatus: CardStatus?
    let action: CardAction
    let date: Date
    
}
