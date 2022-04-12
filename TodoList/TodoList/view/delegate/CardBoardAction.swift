//
//  CardBoardViewAction.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/12.
//

import Foundation

protocol CardBoardAction{
    func historyViewHiddenChanged(_ hiddenState :HiddenState)
}
