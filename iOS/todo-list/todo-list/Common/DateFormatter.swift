//
//  dateFormatter.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/11.
//

import Foundation

extension JSONDecoder {
    var krISO8601: JSONDecoder {
        let decoder = JSONDecoder()
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        dateFormatter.timeZone = TimeZone(abbreviation: "KST")
        dateFormatter.locale = Locale(identifier: "ko_kr")
        decoder.dateDecodingStrategy = .formatted(dateFormatter)
        return decoder
    }
}

extension JSONEncoder {
    var krISO8601: JSONEncoder {
        let encoder = JSONEncoder()
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        dateFormatter.timeZone = TimeZone(abbreviation: "KST")
        dateFormatter.locale = Locale(identifier: "ko_kr")
        encoder.dateEncodingStrategy = .formatted(dateFormatter)
        return encoder
    }
}
