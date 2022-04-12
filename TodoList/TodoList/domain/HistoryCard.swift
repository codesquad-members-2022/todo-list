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
    
    func getTitleString() -> String {
        switch self.action {
        case .add:
            return "\(beforeStatus)에 \(title)을 \(action.name)하였습니다."
        case .update:
            return "\(title)을 \(beforeStatus)에서 \(action.name)하였습니다."
        case .remove:
            return "\(title)을 \(beforeStatus)에서 \(action.name)하였습니다."
        case .move:
            guard let afterStatus = afterStatus else {
                return "이동할 곳이 비어있습니다."
            }
            return "\(title)을 \(beforeStatus)에서 \(afterStatus)로 \(action.name)하였습니다."
        }
    }
}
