//
//  RequestCardsInBoardSession.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

/// 화면 내 카드를 업데이트하는 작업을 수행한다.
///
/// - fetchCardsAll(): 모든 보드의 카드를 가져온다.
/// - fetchCardsInBoard(in:BoardData): 특정 보드의 카드를 가져온다.
class CardsUpdateDataTask: SessionConfiguration
{
    
    init?(as string: String) {
        super.init(as: string, using: nil, in: nil, type: .responsiveData)
    }
    
    func createCard(_ data: ScreenCardParameter, completionHandler: @escaping (CardData?)->Void)
    {
    }
    
    func readCard(from objectId: String, completionHandler: @escaping (CardData?)->Void)
    {
    }
    
    func updateCard(_ data: CardData, completionHandler: @escaping (CardData?)->Void)
    {
    }
    
    func deleteCard(from objectId: String, completionHandler: @escaping (CardData?)->Void)
    {
    }
    
    struct ScreenCardParameter
    {
        var title: String
        var contents: String
        var status: Int
        var updateDate: String
    }
}
