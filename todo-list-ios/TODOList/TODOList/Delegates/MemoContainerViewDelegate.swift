import Foundation

protocol MemoContainerViewDelegate: AnyObject {
    func addButtonDidTap(containerType: MemoContainerType)
}
