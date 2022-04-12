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
    
    func fetchUser(completionHandler: @escaping (Result<String,DataTaskError>) -> Void) {
        guard let url = api.toURL(type: .user) else {
            completionHandler(.failure(.invalidURL))
            return
        }
        let request = URLRequest(url: url)
        self.session.dataTask(with: request) { data, response, error in
            
            guard let httpResponse = response as? HTTPURLResponse,
               let fields = httpResponse.allHeaderFields as? [String: String] else {
                completionHandler(.failure(.notConvertdecode))
                return
            }

            let cookies = HTTPCookie.cookies(withResponseHeaderFields: fields, for: url)
            HTTPCookieStorage.shared.setCookies(cookies, for: response?.url, mainDocumentURL: nil)
            guard let cookie_userId = cookies.filter({ $0.name == "userId" }).first else {
                completionHandler(.failure(.notConvertdecode))
                return
            }
            completionHandler(.success(cookie_userId.value))
        }.resume()
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
        case .all, .user:
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
    case user
    var description: String {
        switch self {
        case .all:
            return "/todolist"
        case .user:
            return "/user"
        }
    }
}

enum DataTaskError: Error {
    case notConnect
    case invalidURL
    case notConvertdecode
}
