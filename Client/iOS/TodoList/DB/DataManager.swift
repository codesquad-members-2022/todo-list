import Foundation
import CoreData

enum BoardType: CustomStringConvertible {
    case all
    case todo
    case progress
    case done
    
    var description: String {
        return "\(self)"
    }
}

// MARK:- DataManager
protocol DataManager {
    func insertCard(boardType: BoardType, id: Int, title: String, content: String)
    func fetchAllCard(where boardType: BoardType) -> [Card]
}


class CoreDataManager: DataManager {
    static var shared = CoreDataManager()
    
    private init() {}
        
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
    
    func insertCard(boardType: BoardType, id: Int, title: String, content: String) {
        guard let entity = entity else {
            return
        }
        let object = NSManagedObject(entity: entity, insertInto: context)
        object.setValue(boardType, forKey: "type")
        object.setValue(id, forKey: "cardId")
        object.setValue(content, forKey: "content")
        object.setValue(title, forKey: "title")
        saveContext()
    }
    
    func fetchAllCard(where boardType: BoardType) -> [Card] {
        do {
            let fetchResults = try context.fetch(TaskBoard.fetchRequest()) as! [TaskBoard]
            let results = fetchResults
                .map{ Card.init(cardId: Int($0.cardId), cardTitle: $0.title!, cardContent: $0.content!, boardName: $0.type!) }
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
