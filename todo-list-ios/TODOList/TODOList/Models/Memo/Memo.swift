import Foundation

final class Memo: NSObject, Codable {

    let title: String
    let content: String
    let name: String
    
    init(title: String, content: String, name: String) {
        self.title = title
        self.content = content
        self.name = name
        super.init()
    }
    
}

extension Memo: NSItemProviderWriting, NSItemProviderReading {
    
    static var readableTypeIdentifiersForItemProvider: [String] {
        return ["itemProviderForMemoObject"]
    }
    
    static var writableTypeIdentifiersForItemProvider: [String] {
        return ["itemProviderForMemoObject"]
    }
    
    func loadData(withTypeIdentifier typeIdentifier: String, forItemProviderCompletionHandler completionHandler: @escaping (Data?, Error?) -> Void) -> Progress? {
        let progress = Progress(totalUnitCount: 100)
        
        do {
            let data = try JSONEncoder().encode(self)
            progress.completedUnitCount = 100
            completionHandler(data,nil)
        } catch {
            completionHandler(nil,error)
        }
        
        return progress
    }
    
    static func object(withItemProviderData data: Data, typeIdentifier: String) throws -> Memo {
        do {
            let memo = try JSONDecoder().decode(Memo.self, from: data)
            return memo
        } catch {
            fatalError()
        }

    }

}



