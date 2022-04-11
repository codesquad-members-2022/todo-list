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

extension Notification.Name {
    static let actionFlowButtonTapped: Notification.Name = Notification.Name("actionFlowButtonTapped")
    static let actionFlowCloseButtonTapped: Notification.Name = Notification.Name("actionFlowCloseButtonTapped")
    static let addTaskButtonTapped: Notification.Name = Notification.Name("addTaskButtonTapped")
    static let editButtonTapped: Notification.Name = Notification.Name("editButtonTapped")
    static let cancelButtonTapped: Notification.Name = Notification.Name("cancelButtonTapped")
}
