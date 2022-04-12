import Foundation

struct NetworkManager {
    
    static let url = URL(string: "http://worldclockapi.com/api/json/est/now") // 아무 URL사용
    
    static private func makeRequest(data: Card?, httpMethod: HttpMethod,
                                    targetColumnId: String?, targetCardId: Int?) -> URLRequest? {
        
        guard let url = url else {return nil}
        
        var request = URLRequest(url: url)
        request.httpMethod = httpMethod.rawValue
        
        if let data = data, let id = data.id {
            let data = ["id": String(id), "subject": data.title,
                        "contents": data.body, "sectionId": data.listName,
                        "author": data.caption.rawValue, "createdAt": "\(data.createdTime)",
                        "targetColumnId": "\(targetColumnId)", "targetCardId": "\(targetCardId)"]
            let encoder = JSONEncoder()
            
            guard let encodedData = try? encoder.encode(data) else {return nil}
            request.httpBody = encodedData
            request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        }
        
        return request
    }
    
    static func sendRequest(data: Card?, httpMethod: HttpMethod,
                            targetColumnId: String?, targetCardId: Int?,
                            completionHandler: @escaping(Result<Card, NerworkError>) -> Void) {
        guard let request = makeRequest(data: data,
                                        httpMethod: httpMethod,
                                        targetColumnId: targetColumnId,
                                        targetCardId: targetCardId) else {return}
        
        let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
            
            if error != nil {
                return completionHandler(.failure(.transportError))
            }
            
            guard let data = data else {
                return completionHandler(.failure(.noDataError))
            }
            
            guard let response = response as? HTTPURLResponse else {return}
            let statusCode = response.statusCode
            
            guard 200..<300 ~= statusCode else {
                return completionHandler(.failure(.serverError(statusCode: statusCode)))
            }
            
            let decoder = JSONDecoder()
            
            if let decodedData = try? decoder.decode(Card.self, from: data) {
                completionHandler(.success(decodedData))
            } else {
                completionHandler(.failure(.decodingError))
            }
        }
        
        task.resume()
    }
}

enum HttpMethod: String {
    case GET
    case POST
    case PATCH
    case DELETE
}

enum NerworkError: Error {
    case transportError
    case noDataError
    case serverError(statusCode: Int)
    case decodingError
}
