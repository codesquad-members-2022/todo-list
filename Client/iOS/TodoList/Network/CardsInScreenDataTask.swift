//
//  CardsInScreenDataTask.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

/// 화면 내 카드를 불러오는 여러 작업을 수행한다.
///
/// - fetchCardsAll(): 모든 보드의 카드를 가져온다.
/// - fetchCardsInBoard(in:BoardData): 특정 보드의 카드를 가져온다.
class CardsInScreenDataTask: SessionConfiguration
{
    
    override init?(
        as string: String,
        using delegate: URLSessionDelegate?,
        in queue: OperationQueue? = nil,
        type: NSURLRequest.NetworkServiceType = .default
    ) {
        super.init(as: string, using: delegate, in: queue, type: type)
    }
    
    func fetchCardsAll(completionHandler: @escaping ([[CardData]])->Void)
    {
    }
    
    func fetchCardsInBoard(completionHandler: @escaping ([CardData])->Void)
    {
    }
}
