import Foundation

struct NameSpace {
    enum nib {
        static let taskCardListView: String = "TaskCardListView"
        static let taskCardViewCell: String = "TaskCardViewCell"
        static let editCardView: String = "EditCardView"
        static let actionCardViewCell: String = "ActionCardViewCell"
    }
    
    enum identifier {
        static let popupViewController: String = "popupViewController"
        static let taskCardViewCell: String = "taskCardViewCell"
        static let actionCardViewCell: String = "actionCardViewCell"
    }
    
}

enum APIResponse {
    static let goodRequest: Int = 200
    static let itemCreated: Int = 201
    static let badRequest: Int = 400
    static let alreadyExist: Int = 409
}

enum API {
    static let baseURL: String = "http://ec2-54-253-23-157.ap-southeast-2.compute.amazonaws.com/"
    static let postURL: String = baseURL + "todo"
    static let getTodosURL: String = baseURL + "todos"
    static let getEventURL: String = baseURL + "events"
    static var putURL: (String) -> String = { id in baseURL + "todo/\(id)" }
    static let deleteURL: (String) -> String = { id in baseURL + "todo/\(id)" }
}

enum NotificationKeyValue {
    static let targetViewTitle = "targetViewTitle"
    static let targetData = "targetData"
    static let postTaskData = "postTaskData"
    static let getTaskData = "getTaskData"
}

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
