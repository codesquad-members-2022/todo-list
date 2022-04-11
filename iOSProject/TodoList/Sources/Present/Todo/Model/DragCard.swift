//
//  DragCard.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/11.
//

import Foundation
import UniformTypeIdentifiers

class DragCard: NSObject, Codable {
    
    let card: Card?
    
    override init() {
        card = nil
        super.init()
    }
    
    init(card: Card) {
        self.card = card
    }
}

extension DragCard: NSItemProviderWriting {
    static var writableTypeIdentifiersForItemProvider: [String] {
        [UTType.data.identifier]
    }
    
    func loadData(withTypeIdentifier typeIdentifier: String, forItemProviderCompletionHandler completionHandler: @escaping (Data?, Error?) -> Void) -> Progress? {
        let progress = Progress(totalUnitCount: 100)
        do {
            let data = try JSONEncoder().encode(self)
            progress.completedUnitCount = 100
            completionHandler(data, nil)
        } catch {
            completionHandler(nil, error)
        }
        return progress
    }
}

extension DragCard: NSItemProviderReading {
    static var readableTypeIdentifiersForItemProvider: [String] {
        [UTType.data.identifier]
    }
    
    static func object(withItemProviderData data: Data, typeIdentifier: String) throws -> Self {
        do {
            let dropCard = try JSONDecoder().decode(DragCard.self, from: data)
            return dropCard as! Self
        } catch {
            fatalError()
        }
    }
    
    
}
