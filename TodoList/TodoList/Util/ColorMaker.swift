//
//  ColorMaker.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/13.
//

import UIKit

enum ColorMaker{
    case lightGray0
    case lightGray1
    case black
}

extension ColorMaker: RawRepresentable{
    typealias RawValue = UIColor
    
    init?(rawValue: RawValue) {
        switch rawValue{
        case #colorLiteral(red: 0.961, green: 0.961, blue: 0.969, alpha: 1):
            self = .lightGray0
        case #colorLiteral(red: 0.004, green: 0.004, blue: 0.004, alpha: 1):
            self = .black
        case #colorLiteral(red: 0.741, green: 0.741, blue: 0.741, alpha: 1):
            self = .lightGray1
        default: return nil
        }
    }
    
    var rawValue: RawValue{
        switch self {
        case .lightGray0:
            return #colorLiteral(red: 0.961, green: 0.961, blue: 0.969, alpha: 1)
        case .black:
            return #colorLiteral(red: 0.004, green: 0.004, blue: 0.004, alpha: 1)
        case .lightGray1:
            return #colorLiteral(red: 0.741, green: 0.741, blue: 0.741, alpha: 1)
        }
    }
    
    func getRawValue() -> RawValue{
        return self.rawValue
    }
}
