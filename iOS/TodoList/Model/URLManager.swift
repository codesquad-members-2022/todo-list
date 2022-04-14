import Foundation

struct URLManager<T: Codable> {
    static func request(api: API, completionHandler: @escaping (T) -> Void) {
        guard let request = api.urlRequest else { return }
        URLSession.shared.dataTask(with: request) {(data, response, error) in
            guard error == nil else { return }
            guard let response = response as? HTTPURLResponse else { return }
            guard (200..<300).contains(response.statusCode) else { return }
            guard let data = data else { return }
            if let result = try? JSONDecoder().decode(T.self, from: data) {
                completionHandler(result)
            }
        }.resume()
    }
}
