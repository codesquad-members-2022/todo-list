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
                string += String(activity.newColumnName)
                string += "에 "
                string += String(activity.cardTitle)
                string += "를 등록하였습니다."
            case "REMOVE":
                string += String(activity.newColumnName)
                string += "에 "
                string += String(activity.cardTitle)
                string += "를 삭제하였습니다."
            case "MOVE":
                string += String(activity.cardTitle)
                string += "를 "
                string += String(activity.oldColumnName)
                string += "에서 "
                string += String(activity.newColumnName)
                string += "로 "
                string += "을 이동하였습니다."
            case "UPDATE":
                string += String(activity.newColumnName)
                string += "에 "
                string += String(activity.cardTitle)
                string += "를 수정하였습니다."
            default:
                print("\(activity.action) NOT FOUND")
            }
            return ActivityBody(text: string)
        case .footer:
            //TODO: - DataFormatter 이용하여 "~분전" 설정
            string += activity.modifiedDate
            return ActivityFooter(text: string)
        }
    }
}
