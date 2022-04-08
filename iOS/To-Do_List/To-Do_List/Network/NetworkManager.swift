
import Foundation

final class NetworkManager {
    private var config = URLSessionConfiguration.default
    private var session = URLSession(configuration:.default)
    
    func getRequest<T:Decodable>(endpoint:Endpointable, completion:@escaping (Result<T,NetworkError>) -> Void) {
        //handling urlError
        guard let url = URL(string:endpoint.url) else {
            completion(.failure(.invalidURL))
            return
        }
        var urlRequest = URLRequest(url: url)
        
        //HTTP Method
        urlRequest.httpMethod = endpoint.httpMethod
        
        //HTTP Headers
        endpoint.headers?.forEach({ (key: String, value: Any) in
            urlRequest.setValue(value as? String , forHTTPHeaderField: key)
        })
        
        let dataTask = session.dataTask(with: urlRequest) { [weak self] data, response, error in
            guard let self = self else { return }
            //handling transportError
            if let error = error {
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
                let decoder = JSONDecoder()
                let data = try decoder.decode(T.self, from: data)
                completion(.success(data))
            }
            catch {
                completion(.failure(.decodingError))
            }
        }
        dataTask.resume()
    }
    
    func postRequest<T:Decodable>(endpoint:Endpointable, completion: @escaping((Result<T,NetworkError>) -> Void)) {
        //handling urlError
        guard let url = URL(string:endpoint.url) else {
            completion(.failure(.invalidURL))
            return
        }
        var urlRequest = URLRequest(url: url)
        
        //HTTP Method
        urlRequest.httpMethod = endpoint.httpMethod
        
        //handling encodingError
        do {
            urlRequest.httpBody = try JSONEncoder().encode(endpoint.body as? NetworkResult)
        }
        catch {
            completion(.failure(.encodingError))
        }
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
