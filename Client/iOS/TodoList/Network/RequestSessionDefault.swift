//
//  RequestCardsInBoardSession.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

class HistoryInPopupDataTask: SessionConfiguration {
    override init?(as string: String, using delegate: URLSessionDelegate?, in queue: OperationQueue? = nil, type: NSURLRequest.NetworkServiceType = .default) {
        super.init(as: string, using: delegate, in: queue, type: type)
    }
}
