import Foundation

enum HTTPMethod: String {
    case post = "POST"
    case get = "GET"
    case put = "PUT"
    case delete = "DELETE"
}

enum HTTPError: Error, CustomStringConvertible {
    case internetDisconnectedError
    case invalidURLError
    case invalidRequestError
    case invalidResponseError
    case unknownError
    
    var description: String {
        switch self {
        case .internetDisconnectedError:
            return "Internet is Disconnected"
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
