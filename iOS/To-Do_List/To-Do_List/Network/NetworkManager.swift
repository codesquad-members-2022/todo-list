
import Foundation

final class NetworkManager {
    private var signUpURL = URL(string:"http://13.125.161.84:8082/api/read/cards")
    private var config = URLSessionConfiguration.default
    private var session = URLSession(configuration:.default)
    
    func getRequest<T:Decodable>(completion:@escaping (Result<T,NetworkError>) -> Void) {
        //handling urlError
        guard let signUpURL = signUpURL else {
            completion(.failure(.invalidURL))
            return
        }
        
        var urlRequest = URLRequest(url: signUpURL)
        urlRequest.httpMethod = HttpMethod.get
        
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
    
    func postRequest<T:Decodable,U:Encodable>(postBody:U, completion: @escaping((Result<T,NetworkError>) -> Void)) {
        //handling urlError
        guard let signUpURL = signUpURL else {
            completion(.failure(.invalidURL))
            return
        }
        
        var urlRequest = URLRequest(url: signUpURL)
        urlRequest.httpMethod = HttpMethod.post
        
        //handling encodingError
        do {
            urlRequest.httpBody = try JSONEncoder().encode(postBody)
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
    
    func url(_ url:URL) {
        self.signUpURL = url
    }
    
    private func getStatusCode(response:URLResponse?) -> Int? {
        guard let httpResponse = response as? HTTPURLResponse else { return nil }
        return httpResponse.statusCode
    }
}
