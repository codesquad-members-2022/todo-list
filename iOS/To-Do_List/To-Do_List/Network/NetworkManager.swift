
import Foundation

protocol URLSessionDataTaskable {
        func dataTask(with request: URLRequest,
                          completionHandler: @escaping (Data?, URLResponse?, Error?) -> Void) -> URLSessionDataTask
}

extension URLSession:URLSessionDataTaskable {}


final class NetworkManager {
    private var session:URLSessionDataTaskable

    init(session:URLSessionDataTaskable) {
        self.session = session
    }
    
    func request<T:Decodable>(endpoint:Endpointable, completion: @escaping((Result<T,NetworkError>) -> Void)) {
        //handling urlError
        let endpointURL = endpoint.getURL()
        guard let url = URL(string:endpointURL) else {
            completion(.failure(.invalidURL))
            return
        }
        var urlRequest = URLRequest(url: url)
        
        //HTTP Method
        let httpMethod = endpoint.getHttpMethod().rawValue
        urlRequest.httpMethod = httpMethod
        
        //handling encodingError if endpoint has body
        if let body = endpoint.getBody() {
            do {
                urlRequest.httpBody = try JSONEncoder().encode(body as? Board)
            }
            catch {
                completion(.failure(.encodingError))
            }
        }
        dataTask(urlRequest: urlRequest, completion: completion)
    }
    
    func dataTask<T:Decodable>(urlRequest: URLRequest, completion: @escaping((Result<T,NetworkError>) -> Void)) {
        
        let dataTask = session.dataTask(with: urlRequest) { [weak self] data, response, error in
            guard let self = self else { return }
            //handling transportError
            if let error = error  {
                completion(.failure(.transportError(error)))
                return
            }
            //handling NoDataError
            guard let data = data else {
                completion(.failure(.noData))
                return
            }
            //handling ServerError
            guard let statusCode = self.getStatusCode(response: response) else { return }
            guard 200..<300 ~= statusCode else {
                completion(.failure(.serverError(statusCode: statusCode)))
                return
            }
            
            //handling DecodingError
            do {
                let fetchedData = try JSONDecoder().decode(T.self, from: data)
                completion(.success(fetchedData))
            }
            catch {
                completion(.failure(.decodingError))
                
            }
        }
        dataTask.resume()
        
    }
    
    func session(_ urlSession: URLSession) {
        self.session = urlSession
    }
    
    private func getStatusCode(response:URLResponse?) -> Int? {
        guard let httpResponse = response as? HTTPURLResponse else { return nil }
        return httpResponse.statusCode
    }
}
