//
//  Date+Extension.swift
//  Signup
//
//  Created by seongha shin on 2022/03/29.
//

import Foundation

extension Date {    
    func toString(format: String) -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = format
        return dateFormatter.string(from: self)
    }
}
