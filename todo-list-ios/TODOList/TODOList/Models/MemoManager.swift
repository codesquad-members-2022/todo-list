import Foundation

class MemoManager {
    
    enum ObserverInfoKey: String {
        case memoDidAdd = "memoDidAdd"
    }
    
    
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
    
    func insertSelectedMemoModel(containerType: MemoStatus, index: Int, memo: Memo) {
        memoTableViewModels[containerType]?.insert(memo, at: index)
    }
    
    func appendMemoModels(containerType: MemoStatus, memos:[Memo]) {
        memoTableViewModels[containerType]?.append(contentsOf: memos)
    }
    
    func getDesignatedMemosCount(containerType: MemoStatus)-> Int {
        return memoTableViewModels[containerType]?.count ?? 0
    }
    
    func getDesignatedMemoModel(containerType: MemoStatus, index: Int)-> Memo? {
        return memoTableViewModels[containerType]?[index] ?? nil
    }
    
    func convertStringToURL(url: String) -> URL? {
        guard let url = URL(string: url) else {
            return nil
        }
        return url
    }
    
    func sendModelDataToNetworkManager(memo: Memo, taskType: Task, methodType: HTTPMethod) {
        guard let data = JSONHandler.convertObjectToJSON(model: memo) else { return }
        guard let url = convertStringToURL(url: EndPoint.url + taskType.path) else { return }
        NetworkHandler.request(data: data, url: url, methodType: methodType, taskType: taskType, responseHandler: self)
    }
    
}

extension MemoManager: HttpResponseHandlable {
    func handleSuccess(data: Data, taskType: Task) {
        //decode json into object
        //let model = JSONHandler.convertJSONToObject(data: data)
        
        //send object to model layer via NotificationCenter
        switch taskType {
        case .memoList:
            return
        case .memoAdd:
            NotificationCenter.default.post(name: .MemoDidAdd, object: self, userInfo: [:])
            return
        case .memoContentUpdate:
            return
        case .memoStatusUpdate:
            return
        case .memoDelete:
            return
        }
    }
    
    func handleFailure(error: Error) {
        //잘못된 응답
    }
}

