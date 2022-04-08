//
//  CardsFetchingDataTask.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

class CardsFetchingDataTask: CardHTTPRequest
{
    
    private let encoder = JSONEncoder()
    private let decoder = JSONDecoder()
    
    override init?(
        as string: String,
        using delegate: URLSessionDelegate?,
        in queue: OperationQueue? = nil,
        type: NSURLRequest.NetworkServiceType = .default
    ) {
        super.init(as: string, using: delegate, in: queue, type: type)
    }
    
    func fetchCardsAll(completionHandler: @escaping ([[CardData]]?)->Void)
    {
        do {
            doGetRequest(url: try FetchingURL.fetchAll.toURL(), parameter: nil) { [weak self] taskResult in
                
                guard let data = try? taskResult.get() else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(try? self?.decoder.decode([[CardData]].self, from: data))
            }
        } catch {
            Log.error(error)
            completionHandler(nil)
        }
    }
    
    func fetchCardsInBoard(completionHandler: @escaping ([CardData]?)->Void)
    {
        do {
            doGetRequest(url: try FetchingURL.oneFetch.toURL(), parameter: nil) { [weak self] taskResult in
                
                guard let data = try? taskResult.get() else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(try? self?.decoder.decode([CardData].self, from: data))
            }
        } catch {
            Log.error(error)
            completionHandler(nil)
        }
    }
}

extension CardsFetchingDataTask {
    enum FetchingURL: String {
        case fetchAll = "https://fetchAll.com"
        case oneFetch = "https://oneFetch.com"
        
        func toURL() throws -> URL {
            if let url = URL(string: self.rawValue) {
                return url
            } else {
                throw CardFetchingError.CardURLError
            }
        }
        
        func toURLComponent() throws -> URLComponents {
            if let comp = URLComponents(string: self.rawValue) {
                return comp
            } else {
                throw CardFetchingError.CardURLError
            }
        }
    }
    
    enum CardFetchingError: String, Error {
        case CardFetchAllError = "Card를 모두 가져오는 중 발생하였습니다."
        case CardOneFetchError = "한 개의 Card를 발생하였습니다."
        case CardURLError = "적절하지 않은 URL입니다."
    }
}
