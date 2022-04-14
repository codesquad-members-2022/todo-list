import Foundation

enum API {
    case post(_ card: RequestCardData)
    case get
    case put(_ id: Int, _ card: RequestCardData)
    case delete(_ id: Int)
    case events
    
    private var baseURL: String {
        return "http://ec2-54-253-23-157.ap-southeast-2.compute.amazonaws.com/"
    }
    private var url: URL? {
        switch self {
        case .post :
            return URL(string: self.baseURL + "todo")
        case .get :
            return URL(string: self.baseURL + "todos")
        case .put(let id, _) :
            return URL(string: self.baseURL + "todo/\(id)")
        case .delete(let id) :
            return URL(string: self.baseURL + "todo/\(id)")
        case .events :
            return URL(string: self.baseURL + "events")
        }
        
    }
    private var method: String {
        switch self {
        case .post :
            return "POST"
        case .get :
            return "GET"
        case .put :
            return "PUT"
        case .delete :
            return "DELETE"
        case .events :
            return "GET"
        }
    }
    
    private var body: Data? {
        switch self {
        case .post(let card) :
            return try? JSONEncoder().encode(card)
        case .put(_, let card) :
            return try? JSONEncoder().encode(card)
        default:
            return nil
        }
        
    }
    
    var urlRequest: URLRequest? {
        guard let url = self.url else { return nil }
        var request = URLRequest(url: url)
        request.httpMethod = self.method
        request.httpBody = self.body
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        return request
    }
}
