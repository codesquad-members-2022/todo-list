//
//  RequestCardsInBoardSession.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

/// 업데이트, 삽입 등 사용자의 작업이 실행된 뒤 Response까지 기다려야 하는 세션
///
/// config : URLSessionConfiguration.ephemeral
/// delegateQueue : OperationQueue.main
class HistoryInPopupDataTask: SessionConfiguration {
    override init?(as string: String, using delegate: URLSessionDelegate?, in queue: OperationQueue? = nil, type: NSURLRequest.NetworkServiceType = .default) {
        super.init(as: string, using: delegate, in: queue, type: type)
    }
}
