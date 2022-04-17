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
    func handleSuccess(data: Data, successHandler:((Data)->Void)?)
    func handleFailure(error: Error)
}

final class NetworkHandler {
    
    private static let session: URLSession = {
        let session = URLSession(configuration: .default)
        return session
    }()
    
    static func request(data: Data, url: URL, methodType: HTTPMethod, responseHandler: HttpResponseHandlable, successHandler:@escaping (Data)->Void) {
        var request = URLRequest(url: url)
        request.httpMethod = methodType.rawValue
        request.addValue("application/json", forHTTPHeaderField: "content-type")
        request.httpBody = data
        session.dataTask(with: request, completionHandler: { data, response, error in
            if let error = error {
                handleResponse(target: responseHandler, result: .failure(HTTPError.normalError(error: error)), successHandler: nil)
                return
            }
            
            guard let data = data, let response = response else {
                handleResponse(target: responseHandler, result: .failure(HTTPError.invalidResponseError), successHandler: nil)
                return
            }
            
            handleResponse(target: responseHandler, result: .success(data), successHandler: successHandler)
        }).resume()
    }
    
    /*
        서버가 비활성화되있을 경우,
        아래 mockRequest를 호출해서, 응답을 받았다고 가정하고 응답 처리 로직을 실행
     */
    static func mockRequest(data: Data, url: URL, methodType: HTTPMethod, responseHandler: HttpResponseHandlable, successHandler:@escaping (Data)->Void) {
        guard let path = Bundle.main.url(forResource: "sample", withExtension: "json") else { return }
        guard let data = try? Data(contentsOf: path) else {
            print("json not exists")
            return
        }
        handleResponse(target: responseHandler, result: .success(data), successHandler: successHandler)
    }
    
    static func handleResponse(target: HttpResponseHandlable, result: Result<Data,HTTPError>, successHandler:((Data)->Void)?) {
        switch result {
        case .success(let data):
            target.handleSuccess(data: data, successHandler: successHandler)
        case .failure(let error):
            target.handleFailure(error: error)
        }
    }
}
