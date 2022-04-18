import Foundation

protocol PopupCardViewDelegate: AnyObject {
    func popupCardCancelButtonDidTap()
    func popupCardOkButtonDidTap(title: String?, content: String?, status: MemoStatus?)
}
