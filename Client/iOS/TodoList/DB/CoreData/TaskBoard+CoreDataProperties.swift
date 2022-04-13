//
//  TaskBoard+CoreDataProperties.swift
//  TodoList
//
//  Created by jsj on 2022/04/13.
//
//

import Foundation
import CoreData


extension TaskBoard {

    @nonobjc public class func fetchRequest() -> NSFetchRequest<TaskBoard> {
        return NSFetchRequest<TaskBoard>(entityName: "TaskBoard")
    }

    @NSManaged public var cardId: Int64
    @NSManaged public var title: String?
    @NSManaged public var content: String?
    @NSManaged public var type: String?

}

extension TaskBoard : Identifiable {

}
