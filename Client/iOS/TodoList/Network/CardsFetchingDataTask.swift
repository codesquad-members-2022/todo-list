//
//  CardsFetchingDataTask.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

struct Team13API: ServerAPI {
    let endpoint: String = "http://13.125.216.180:8080"
}


class DataTask: SessionDataTask {
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
        let request = URLRequest(url: url)
        self.session.dataTask(with: request) { data, response, error in
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

// MARK:- Cards
struct Cards: Codable {
    let todo: [Card]
}

struct Card: Codable {
    let cardId: Int
    let cardTitle: String
    let cardContent: String
    let boardName: String
}

// MARK:- ServerAPI
protocol ServerAPI {
    var endpoint: String { get }
}

extension ServerAPI {
    func getUrlString(type: URLType) -> String {
        switch type {
        case .all:
            return endpoint+"\(type)"
        }
    }
    
    func toURL(type: URLType) -> URL? {
        guard let url = URL(string: endpoint+"\(type)") else {
            return nil
        }
        return url
    }
}

enum URLType: CustomStringConvertible {
    case all
    var description: String {
        switch self {
        case .all:
            return "/todolist"
        }
    }
}

enum DataTaskError: Error {
    case notConnect
    case invalidURL
    case notConvertdecode
}
