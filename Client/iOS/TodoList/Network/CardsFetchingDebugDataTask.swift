import Foundation

class DebugDataTask: SessionDataTask {
    private let encoder = JSONEncoder()
    private let decoder = JSONDecoder()
    private let api: ServerAPI
    
    convenience init?(api: ServerAPI) {
        self.init(api: api, using: nil)
    }
    
    init?(api: ServerAPI,
         using delegate: URLSessionDelegate?,
         in queue: OperationQueue? = nil,
         type: NSURLRequest.NetworkServiceType = .default
         ) {
        self.api = api
        let urlString = api.getUrlString(type: .all)
        super.init(as: urlString, using: delegate, in: queue, type: type)
    }
    
    func fetchAll<T: Codable>(dataType: T.Type, completionHandler: @escaping (Result<T,DataTaskError>) -> Void) {
        guard let url = api.toURL(type: .all) else {
            completionHandler(.failure(.invalidURL))
            return
        }
        var request = URLRequest(url: url)
        
        #if DEBUG
        request.setValue("true", forHTTPHeaderField: "debug")
        #endif
        
        self.session.dataTask(with: request) { data, response, error in
            
            guard error == nil else {
                completionHandler(.failure(.notConnect))
                return
            }
            
            guard let data = data,
                let decodedData = try? self.decoder.decode(T.self, from: data) else {
                completionHandler(.failure(.notConvertdecode))
                return
            }
            
            completionHandler(.success(decodedData))
        }.resume()
    }
    
    private func makeURLComponents(from string: String? = nil, using parameter: [String: String]? = nil) -> URLComponents? {
        
        if let string = string {
            urlString = string
        }
        guard let url = URL(string: urlString) else { return nil }
        
        var queryItems = [URLQueryItem]()
        
        if let parameter = parameter {
            for param in parameter {
                queryItems.append(URLQueryItem(name: param.key, value: param.value))
            }
        }
        
        var urlComp = URLComponents(url: url, resolvingAgainstBaseURL: false)
        urlComp?.queryItems = queryItems
        return urlComp
    }
}
