
import Foundation

final class NetworkManager {
    private var signUpURL = URL(string:"http://13.125.161.84:8082/api/columns/readonly")
    private var config = URLSessionConfiguration.default
    private var session = URLSession(configuration:.default)
    
    func getRequest<T:Decodable>(completion:@escaping (Result<T,NetworkError>) -> Void) {
        //is URL available?
        guard let signUpURL = signUpURL else { return }
        
        var urlRequest = URLRequest(url: signUpURL)
        urlRequest.httpMethod = HttpMethod.get
        
        let dataTask = session.dataTask(with: urlRequest) { [weak self] data, response, _ in
            guard let self = self else { return }
            guard let data = data, self.isResponseClear(response: response) == true
            else {
                completion(.failure(.responseError))
                return
            }
            
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
        //is URL available?
        guard let signUpURL = signUpURL else { return }
        var urlRequest = URLRequest(url: signUpURL)
        urlRequest.httpMethod = HttpMethod.post
        
        do {
            urlRequest.httpBody = try JSONEncoder().encode(postBody)
            
            //is response clear?
            let dataTask = session.dataTask(with: urlRequest) { [weak self] data, response, _ in
                    guard let self = self else { return }
                    guard let data = data, self.isResponseClear(response: response) == true
                    else {
                    completion(.failure(.responseError))
                    return
                }
            
                do {
                    //then decode
                    let messageData = try JSONDecoder().decode(T.self, from: data)
                    completion(.success(messageData))
                }
                    //else failure
                catch {
                    completion(.failure(.decodingError))
                    
                }
            }
            dataTask.resume()
        }
        
        catch {
            completion(.failure(.encodingError))
        }
        
    }
    
    func session(_ urlSession: URLSession) {
        self.session = urlSession
    }
    
    func url(_ url:URL) {
        self.signUpURL = url
    }
    
    private func isResponseClear(response:URLResponse?) -> Bool {
        guard let httpResponse = response as? HTTPURLResponse else { return false }
        if (200..<300) ~= httpResponse.statusCode {
            return true
        } else {
            return false
        }
    }
}
