import Foundation
import CoreData

// MARK:- Cards
struct Card {
    let id: Int
    let title: String
    let contents: String
    let boardName: String
}

enum BoardType: CustomStringConvertible {
    case all
    case todo
    case progress
    case done
    
    var description: String {
        switch self {
        case .todo:
            return "TODO"
        case .progress:
            return "DOING"
        case .done:
            return "DONE"
        default:
            return ""
        }
    }
}

// MARK:- DataManager
protocol PersistenceProvider {
    func insertCard(boardType: BoardType, id: Int, title: String, content: String)
    func insertBoard(type: BoardType, cards: [Card])
    func fetchAllCard(where boardType: BoardType) -> [Card]
}


class BoardPersistenceProvider: PersistenceProvider {
    private lazy var persistentContainer: NSPersistentContainer = {
        let container = NSPersistentContainer(name: "Model")
        container.loadPersistentStores { description, error in
            if let error = error {
                fatalError("Unable to load persistent stores: \(error)")
            }
        }
        return container
    }()
    
    private var context: NSManagedObjectContext {
        return persistentContainer.viewContext
    }
    
    private var entity: NSEntityDescription? {
        return NSEntityDescription.entity(forEntityName: "TaskBoard", in: context)
    }
    
    func insertBoard(type: BoardType, cards: [Card]) {
        let fetchAllCard = fetchAllCard(where: type)
        for card in cards {
            if fetchAllCard.contains(where: { $0.id == card.id }) {
                // TODO:- id 가 같지만 수정되어 필드값이 하나라도 다를 수 있다 -> 모든 필드가 같은지 비교 필요
                continue
            }
            insertCard(boardType: type, id: card.id, title: card.title, content: card.contents)
        }
    }
    
    func insertCard(boardType: BoardType, id: Int, title: String, content: String) {
        guard let entity = entity else {
            return
        }
        let object = NSManagedObject(entity: entity, insertInto: context)
        object.setValue(boardType.description, forKey: "type")
        object.setValue(id, forKey: "cardId")
        object.setValue(content, forKey: "content")
        object.setValue(title, forKey: "title")
        saveContext()
    }
    
    func fetchAllCard(where boardType: BoardType) -> [Card] {
        do {
            let fetchResults = try context.fetch(TaskBoard.fetchRequest()) as! [TaskBoard]
            let results = fetchResults
                .map{ Card(id: Int($0.cardId), title: $0.title!, contents: $0.content!, boardName: $0.type!) }
            
            if boardType == .all {
                return results
            }
            return results.filter{ $0.boardName == boardType.description }
        } catch {
            print(error.localizedDescription)
        }
        return []
    }
    
    private func saveContext(backgroundContext: NSManagedObjectContext? = nil) {
        let context = backgroundContext ?? self.context
        guard context.hasChanges else { return }
        do {
            try context.save()
        } catch let error as NSError {
            print("Error: \(error), \(error.userInfo)")
        }
    }
}
