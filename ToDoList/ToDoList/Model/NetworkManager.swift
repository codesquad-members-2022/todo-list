import Foundation

struct NetworkManager {
    
    static private func makeRequest(networkTarget: NetworkTargetable) -> URLRequest? {
        
        var request = URLRequest(url: networkTarget.url)
        request.httpMethod = networkTarget.method
        
        if let data = networkTarget.parameter {
            let body = try? JSONSerialization.data(withJSONObject: data, options: .init())
            request.httpBody = body
        }
        
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        
        return request
    }
    
    static func sendRequest(networkTarget: NetworkTargetable,
                            completionHandler: @escaping(Result<Card, NerworkError>) -> Void) {
        guard let request = makeRequest(networkTarget: networkTarget) else {return}
        
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

enum NerworkError: Error {
    case transportError
    case noDataError
    case serverError(statusCode: Int)
    case decodingError
}
