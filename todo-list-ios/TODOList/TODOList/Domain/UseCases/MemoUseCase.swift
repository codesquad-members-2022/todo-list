import Foundation

final class MemoUseCase {
    func convertStringToURL(url: String) -> URL? {
        guard let url = URL(string: url) else {
            return nil
        }
        return url
    }
    
    func convertMemoToJSON(memo: Memo) -> Data? {
        return try? JSONEncoder().encode(memo)
    }
    
    func sendDataToManager(data: Data?, methodType: HTTPMethod, path: Path) {
        guard let data = data else {
            return
        }
        
        guard let url = convertStringToURL(url: EndPoint.url + path.rawValue) else { return }
        NetworkManager.request(data: data, url: url, methodType: methodType)
    }
}


enum Path: String {
    case task
}

