import Foundation

class MemoManager {
    
    private (set) var memoTableViewModels: [MemoContainerType: [Memo]] = [.todo:[], .progress:[], .done:[]]
    
    //임시로 생성자에서 테스트할 객체 생성
    init() {
        for containerType in MemoContainerType.allCases {
            for index in 0..<3 {
                let memo = Memo(title: containerType.rawValue, content: "\(index) : 해야할 일의 내용입니다\n할게 너무 많아요\n열심히 하세요", name: "JK \(index)", status: containerType)
                memoTableViewModels[containerType]?.append(memo)
            }
        }
    }
    
    func removeSelectedMemoModel(containerType: MemoContainerType, index: Int) {
        memoTableViewModels[containerType]?.remove(at: index)
    }
    
    func insertSelectedMemoModel(containerType: MemoContainerType, index: Int, memo: Memo) {
        memoTableViewModels[containerType]?.insert(memo, at: index)
    }
    
    func appendMemoModels(containerType: MemoContainerType, memos:[Memo]) {
        memoTableViewModels[containerType]?.append(contentsOf: memos)
    }
    
    func getDesignatedMemosCount(containerType: MemoContainerType)-> Int {
        return memoTableViewModels[containerType]?.count ?? 0
    }
    
    func getDesignatedMemoModel(containerType: MemoContainerType, index: Int)-> Memo? {
        return memoTableViewModels[containerType]?[index] ?? nil
    }
    
}
