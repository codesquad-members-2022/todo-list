import Foundation

extension Notification.Name {
    static let actionFlowButtonTapped: Notification.Name = Notification.Name("actionFlowButtonTapped")
    static let actionFlowCloseButtonTapped: Notification.Name = Notification.Name("actionFlowCloseButtonTapped")
    static let addTaskButtonTapped: Notification.Name = Notification.Name("addTaskButtonTapped")
    static let editButtonTapped: Notification.Name = Notification.Name("editButtonTapped")
    static let cancelButtonTapped: Notification.Name = Notification.Name("cancelButtonTapped")
    
    static let getTaskBoardData: Notification.Name = Notification.Name("getTaskBoardData")
    static let postCardData: Notification.Name = Notification.Name("postCardData")
    static let putCardData: Notification.Name = Notification.Name("putCardData")
    static let deleteCardData: Notification.Name = Notification.Name("deleteCardData")
}
