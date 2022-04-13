//
//  HistoryBoard.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/13.
//

import Foundation

class HistoryBoard{
    private(set) var historyHiddenState: HiddenState = .hidden
    var historyBoardAction: HistoryBoardDelegate?
    
    func historyButtonTapped(){
        switch historyHiddenState{
        case .hidden:
            self.historyHiddenState = .show
        case .show:
            self.historyHiddenState = .hidden
        }
        historyBoardAction?.historyViewHiddenChanged(self.historyHiddenState)
    }
}
