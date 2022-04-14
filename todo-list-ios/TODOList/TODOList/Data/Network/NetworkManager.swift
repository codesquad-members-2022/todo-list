import Foundation

class NetworkManager {
    static func request(data: Data, url: URL, methodType: HTTPMethod) {
        var request = URLRequest(url: url)
        request.httpMethod = methodType.rawValue
        request.addValue("application/json", forHTTPHeaderField: "content-type")
        
        let session = URLSession(configuration: .default)
        session.dataTask(with: request, completionHandler: { data, response, error in
            
        }).resume()
    }
}
