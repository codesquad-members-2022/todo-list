import Foundation

final class JSONHandler {
    
    private static let encoder = JSONEncoder()
    private static let decoder = JSONDecoder()
    
    static func convertObjectToJSON<T: Encodable>(model: T) -> Data? {
        return try? encoder.encode(model)
    }
    
    static func convertJSONToObject<T: Decodable>(data: Data, targetType: T.Type) -> T? {
        return try? decoder.decode(T.self, from: data)
    }
    
}
