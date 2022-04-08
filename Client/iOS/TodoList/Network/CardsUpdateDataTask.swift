//
//  RequestCardsInBoardSession.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

class CardsUpdateDataTask: CardHTTPRequest
{
    
    private let encoder = JSONEncoder()
    private let decoder = JSONDecoder()
    
    init?(as string: String) {
        super.init(as: string, using: nil, in: nil, type: .responsiveData)
    }
    
    func createCard(_ param: ScreenCardParameter, completionHandler: @escaping (CardData?)->Void)
    {
        do {
            doPostRequest(url: try CardManagingURL.create.toURL(), try encoder.encode(param)) { [weak self] data in
                
                guard let data = data else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(try? self?.decoder.decode(CardData.self, from: data))
            }
        } catch {
            print(error)
            completionHandler(nil)
        }
    }
    
    func readCard(from objectId: String, completionHandler: @escaping (CardData?)->Void)
    {
        do {
            doGetRequest(url: try CardManagingURL.read.toURL(), parameter: ["objectId": objectId]) { [weak self] data in
                
                guard let data = data else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(try? self?.decoder.decode(CardData.self, from: data))
            }
        } catch {
            print(error)
            completionHandler(nil)
        }
    }
    
    func updateCard(_ paramData: CardData, completionHandler: @escaping (CardData?)->Void)
    {
        do {
            doPostRequest(url: try CardManagingURL.update.toURL(), try encoder.encode(paramData)) { [weak self] data in
                
                guard let data = data else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(try? self?.decoder.decode(CardData.self, from: data))
            }
        } catch {
            print(error)
            completionHandler(nil)
        }
    }
    
    func deleteCard(from objectId: String, completionHandler: @escaping (CardData?)->Void)
    {
        do {
            doGetRequest(url: try CardManagingURL.delete.toURL(), parameter: ["objectId":objectId]) { [weak self] data in
                
                guard let data = data else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(try? self?.decoder.decode(CardData.self, from: data))
            }
        } catch {
            print(error)
            completionHandler(nil)
        }
    }
}

extension CardsUpdateDataTask {
    struct ScreenCardParameter: Encodable
    {
        var title: String
        var contents: String
        var status: Int
        var updateDate: String
        
        func encode(to encoder: Encoder) throws {
            var container = encoder.container(keyedBy: ScreenCardParameterCodingKey.self)
            try container.encode(title, forKey: .title)
            try container.encode(contents, forKey: .contents)
            try container.encode(status, forKey: .status)
            try container.encode(updateDate, forKey: .updateDate)
        }
        
        enum ScreenCardParameterCodingKey: String, CodingKey {
            case title = "title"
            case contents = "contents"
            case status = "status"
            case updateDate = "updateDate"
        }
    }
    
    enum CardManagingURL: String {
        case create = "https://create.com"
        case read = "https://read.com"
        case update = "https://update.com"
        case delete = "https://delete.com"
        
        func toURL() throws -> URL {
            if let url = URL(string: self.rawValue) {
                return url
            } else {
                throw CardManagingError.CardURLError
            }
        }
        
        func toURLComponent() throws -> URLComponents {
            if let comp = URLComponents(string: self.rawValue) {
                return comp
            } else {
                throw CardManagingError.CardURLError
            }
        }
    }
    
    enum CardManagingError: String, Error {
        case CardCreateError = "Card를 생성 에러가 발생하였습니다."
        case CardReadError = "Card를 불러오기 에러가 발생하였습니다."
        case CardUpdateError = "Card를 수정 에러가 발생하였습니다."
        case CardDeleteError = "Card를 삭제 에러가 발생하였습니다."
        case CardURLError = "적절하지 않은 URL입니다."
    }
}
