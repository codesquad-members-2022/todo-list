import Foundation

enum HTTPMethod: String {
    case post = "POST"
    case get = "GET"
    case put = "PUT"
    case delete = "DELETE"
}

enum HTTPError: Error, CustomStringConvertible {
    case normalError(error: Error)
    case invalidURLError
    case invalidRequestError
    case invalidResponseError
    case unknownError
    
    var description: String {
        switch self {
        case .normalError(let error):
            return error.localizedDescription
        case .invalidURLError:
            return "Invalid URL"
        case .invalidRequestError:
            return "Invalid Request"
        case .invalidResponseError:
            return "Invalid Response"
        case .unknownError:
            return "Unknown Error"
        }
    }
}

protocol HttpResponseHandlable{
    func handleSuccess(data: Data, taskType: Task)
    func handleFailure(error: Error)
}

final class NetworkHandler {
    
    private static let session: URLSession = {
        let session = URLSession(configuration: .default)
        return session
    }()
    
    static func request(data: Data, url: URL, methodType: HTTPMethod, taskType: Task, responseHandler: HttpResponseHandlable) {
        var request = URLRequest(url: url)
        request.httpMethod = methodType.rawValue
        request.addValue("application/json", forHTTPHeaderField: "content-type")
        session.dataTask(with: request, completionHandler: { data, response, error in
            
            if let error = error {
                handleResponse(target: responseHandler, result: .failure(HTTPError.normalError(error: error)), taskType: taskType)
            }
            
            guard let data = data, let response = response else {
                handleResponse(target: responseHandler, result: .failure(HTTPError.invalidResponseError), taskType: taskType)
                return
            }
            
            handleResponse(target: responseHandler, result: .success(data), taskType: taskType)
                
        }).resume()
    }
    
    static func handleResponse(target: HttpResponseHandlable, result: Result<Data,HTTPError>, taskType: Task) {
        switch result {
        case .success(let data):
            target.handleSuccess(data: data, taskType: taskType)
        case .failure(let error):
            target.handleFailure(error: error)
        }
    }
}
