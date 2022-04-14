//
//  ActivityText.swift
//  TodoApp
//
//  Created by YEONGJIN JANG on 2022/04/14.
//

import Foundation

enum TextComponent {
    case body, footer
}


protocol StringData {
    var sort: TextComponent { get }
    var text: String { get set }
}

struct ActivityBody: StringData {
    var sort: TextComponent { .body }
    var text: String
    init(text: String) {
        self.text = text
    }
}

struct ActivityFooter: StringData {
    var sort: TextComponent { .footer }
    var text: String
    init(text: String) {
        self.text = text
    }
}

struct FieldFactory {
    func make(_ component: TextComponent, _ activity: Activity) -> StringData {
        var string: String = ""
        switch component {
        case .body:
            switch activity.action {
            case "ADD":
                string += String(activity.newColumn)
                string += "에 "
                string += String(activity.id)
                string += "을 등록하였습니다."
            case "DELETE":
                string += String(activity.newColumn)
                string += "에 "
                string += String(activity.id)
                string += "을 삭제하였습니다."
            case "MOVE":
                string += String(activity.id)
                string += "을 "
                string += String(activity.oldColumn)
                string += "에서 "
                string += String(activity.newColumn)
                string += "로 "
                string += "을 이동하였습니다."
            case "UPDATE":
                string += String(activity.newColumn)
                string += "에 "
                string += String(activity.id)
                string += "을 수정하였습니다."
            default:
                print("\(activity.action) NOT FOUND")
            }
            return ActivityBody(text: string)
        case .footer:
            //TODO: - DataFormatter 이용하여 "~분전" 설정
            string += activity.modifiedAt
            return ActivityFooter(text: string)
        }
    }
}
