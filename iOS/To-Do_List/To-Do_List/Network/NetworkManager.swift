
import Foundation

final class NetworkManager {
    private var session:URLSession

    init(session:URLSession) {
        self.session = session
    }
    
    func request<T:Decodable>(endpoint:Endpointable, completionHandler: @escaping((Result<T,NetworkError>) -> Void)) {
        //handling urlError
        let endpointURL = endpoint.getURL()
        guard let url = URL(string:endpointURL) else {
            completionHandler(.failure(.invalidURL))
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
                completionHandler(.failure(.encodingError))
            }
        }
        dataTask(urlRequest: urlRequest, completionHandler: completionHandler)
    }
    
    func dataTask<T:Decodable>(urlRequest: URLRequest, completionHandler: @escaping((Result<T,NetworkError>) -> Void)) {
        
        let dataTask = session.dataTask(with: urlRequest) { [weak self] data, response, error in
            guard let self = self else { return }
            //handling transportError
            if let error = error  {
                completionHandler(.failure(.transportError(error)))
                return
            }
            //handling NoDataError
            guard let data = data else {
                completionHandler(.failure(.noData))
                return
            }
            //handling ServerError
            guard let statusCode = self.getStatusCode(response: response) else { return }
            guard 200..<300 ~= statusCode else {
                completionHandler(.failure(.serverError(statusCode: statusCode)))
                return
            }
            
            //handling DecodingError
            do {
                let fetchedData = try JSONDecoder().decode(T.self, from: data)
                completionHandler(.success(fetchedData))
            }
            catch {
                completionHandler(.failure(.decodingError))
                
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
