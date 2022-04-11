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

class DataTask<T: Codable>: CardHTTPRequest {
    private let encoder = JSONEncoder()
    private let decoder = JSONDecoder()
    private let dataType: T.Type
    private let api: ServerAPI
    
    convenience init?(api: ServerAPI, dataType: T.Type) {
        self.init(api: api, dataType: dataType, using: nil)
    }
    
    init?(api: ServerAPI,
         dataType: T.Type,
         using delegate: URLSessionDelegate?,
         in queue: OperationQueue? = nil,
         type: NSURLRequest.NetworkServiceType = .default
         ) {
        self.api = api
        self.dataType = dataType
        let urlString = api.getUrlString(type: .all)
        super.init(as: urlString, using: delegate, in: queue, type: type)
    }
    
    func fetchCardsAll(completionHandler: @escaping (Result<[[T]],DataTaskError>)->Void) {
        guard let url = api.toURL(type: .all) else {
            completionHandler(.failure(.invalidURL))
            return
        }
        doGetRequest(url: url, parameter: nil) { [weak self] taskResult in
            guard let data = try? taskResult.get() else {
                completionHandler(.failure(.notConnect))
                return
            }
            guard let decodedData = try? self?.decoder.decode([[T]].self, from: data) else {
                completionHandler(.failure(.notConvertdecode))
                return
            }
            completionHandler(.success(decodedData))
        }
    }
    
    func fetchCardsInBoard(completionHandler: @escaping (Result<[T], DataTaskError>)->Void) {
        guard let url = api.toURL(type: .all) else {
            completionHandler(.failure(.invalidURL))
            return
        }
        doGetRequest(url: url, parameter: nil) { [weak self] taskResult in
            guard let data = try? taskResult.get() else {
                completionHandler(.failure(.notConnect))
                return
            }
            guard let decodedData = try? self?.decoder.decode([T].self, from: data) else {
                completionHandler(.failure(.notConvertdecode))
                return
            }
            completionHandler(.success(decodedData))
        }
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
