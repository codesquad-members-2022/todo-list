//
//  EditStyle.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/12.
//

enum EditStyle {
    case add
    case editContent(cardInfo : Todo)
    
    var cardId : Int? {
        switch self {
        case .add:
            return nil
        case .editContent(let cardInfo):
            return cardInfo.id
        }
    }
    
    var title:String {
        switch self {
        case .add:
            return "카드 추가"
        case .editContent:
            return "카드 수정"
        }
    }
    
    var headLineText:String {
        switch self {
        case .add:
            return "제목을 입력하세요"
        case .editContent(let cardInfo):
            return cardInfo.title
        }
    }
    
    var contentTextFieldText:String {
        switch self {
        case .add:
            return "내용을 입력하세요"
        case .editContent(let cardInfo):
            return cardInfo.content
        }
    }
    
    var buttonText:String {
        switch self {
        case .add:
            return "등록"
        case .editContent:
            return "수정"
        }
    }
}
