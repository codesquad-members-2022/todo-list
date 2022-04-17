import Foundation
import OSLog

extension NSNotification.Name {
    static let memoDidAdd = NSNotification.Name("MemoDidAddNotification")
    static let memoDidUpdate = NSNotification.Name("MemoDidUpdateNotification")
    static let memoDidDelete = NSNotification.Name("MemoDidDeleteNotification")
    static let networkError = NSNotification.Name("Network Error")
}

enum UserInfoKeys {
    static let memo = "Memo"
}

enum Task {
    case memoList
    case memoAdd
    case memoContentUpdate
    case memoStatusUpdate
    case memoDelete
    
    var path: String {
        switch self {
        case .memoList:
            return ""
        case .memoAdd:
            return "task"
        case .memoContentUpdate:
            return ""
        case .memoStatusUpdate:
            return ""
        case .memoDelete:
            return ""
        }
    }
}

class MemoManager {
    
    enum ObserverInfoKey: String {
        case memoDidAdd = "memoDidAdd"
    }
    
    private (set) var memoTableViewModels: [MemoStatus: [Memo]] = [.todo:[], .progress:[], .done:[]]
    
    func removeSelectedMemoModel(containerType: MemoStatus, index: Int) {
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
    
    /*
        현재 서버가 비활성화된 상태이기 때문에,
        mockRequest를 대신 호출하여, 미리 준비된 JSON을 응답데이터로 받아서 처리하도록 했음
     */
    
    func sendModelDataToNetworkManager(memo: Memo, taskType: Task, methodType: HTTPMethod) {
        guard let data = JSONHandler.convertObjectToJSON(model: memo.toRequestEntity()) else { return }
        guard let url = convertStringToURL(url: EndPoint.url + taskType.path) else { return }
//        NetworkHandler.request(data: data, url: url, methodType: methodType, responseHandler: self) { data in
//            guard let memoResponse = JSONHandler.convertJSONToObject(data: data, targetType: MemoPostResponse.self) else {
//                self.handleFailure(error: HTTPError.invalidResponseError)
//                return
//            }
//            let memo = memoResponse.toResponseDto()
//            self.memoTableViewModels[.todo]?.insert(memo, at: 0)
//            NotificationCenter.default.post(name: .memoDidAdd, object: self, userInfo: [UserInfoKeys.memo:memo])
//        }
        
        NetworkHandler.mockRequest(data: data, url: url, methodType: methodType, responseHandler: self) { data in
            guard let memoResponse = JSONHandler.convertJSONToObject(data: data, targetType: MemoPostResponse.self) else {
                self.handleFailure(error: HTTPError.invalidResponseError)
                return
            }
            let memo = memoResponse.toResponseDto()
            self.memoTableViewModels[.todo]?.insert(memo, at: 0)
            NotificationCenter.default.post(name: .memoDidAdd, object: self, userInfo: [UserInfoKeys.memo:memo])
        }
    }
}

extension MemoManager: HttpResponseHandlable {
    func handleSuccess(data: Data, successHandler: ((Data) -> Void)?) {
        guard let successHandler = successHandler else { return }
        successHandler(data)
    }
    
    func handleFailure(error: Error) {
        let logger = Logger()
        logger.error("\(error.localizedDescription)")
    }
}

