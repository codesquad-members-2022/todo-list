//
//  LogTableViewCell.swift
//  todo-list
//
//  Created by Jason on 2022/04/07.
//

import Foundation
import UIKit

class LogTableViewCell: UITableViewCell {
    private var log = Log(userName: "@sam", title: "HTML/CSS공부하기")
    
    // userName, TimeStamp, 활동내역 모두 같은 Font 사용
    enum common {
        static let commonFont = UIFont(name: "NotoSansKR-Regular", size: 14)
        static let commonAttribute: [NSMutableAttributedString.Key: Any] = [
            .foregroundColor : UIColor.black,
            .font : commonFont ?? UIFont()
        ]
    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }

    //MARK: Username Method
    func makeUserName(userName: String) -> NSAttributedString {
        return NSAttributedString(string: log.userName, attributes: common.commonAttribute)
    }
    
    //MARK: Title Method
    func makeTitle(title: String) -> NSAttributedString {
        return NSAttributedString(string: log.title, attributes: common.commonAttribute)
    }
    
    //MARK: Text & SubText Method
    func makeText() {
        
    }
    
    //MARK: TimeStamp Method
    func makeTimeStamp(timeStamp: Int) -> NSAttributedString {
        let time = log.showTimeStamp()
        let timeStr = "\(time)분전"
        
        return NSAttributedString(string: timeStr, attributes: common.commonAttribute)
    }
    
}


