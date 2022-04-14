import Foundation

protocol PopupCardViewDelegate: AnyObject {
    func popupCardCancelButtonDidTap()
    func popupCardOkButtonDidTap()
    func popupCardOkButtonDidTapTemp(title: String, content: String)
}
