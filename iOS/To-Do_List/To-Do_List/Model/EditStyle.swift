//
//  EditStyle.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/12.
//

enum EditStyle {
    case add
    case editContent
    
    var title:String {
        switch self {
        case .add:
            return "카드 추가"
        case .editContent:
            return "카드 수정"
        }
    }
    
    var headLineTextFieldPlaceholder:String {
        switch self {
        case .add:
            return "제목을 입력하세요"
        case .editContent:
            return ""
        }
    }
    
    var contentTextFieldPlaceholder:String {
        switch self {
        case .add:
            return "내용을 입력하세요"
        case .editContent:
            return ""
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
