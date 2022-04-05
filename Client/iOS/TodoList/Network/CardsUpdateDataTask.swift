//
//  RequestCardsInBoardSession.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

/// 화면 내 카드를 업데이트하는 작업을 수행한다.
///
/// - createCard(:ScreenCardParameter,completionHandler:(CardData?)->Void) : 파라미터에 따라 카드 생성을 요청한다.
/// - readCard(from:String,completionHandler:(CardData?)->Void) : 전달한 ID와 일치하는 카드를 가져온다.
/// - updateCard(:CardData,completionHandler:(CardData?)->Void) : 카드 데이터를 업데이트 한다.
/// - deleteCard(from:String,completionHandler:(CardData?)->Void) : 전달한 ID와 일치하는 카드를 삭제한다.
class CardsUpdateDataTask: SessionConfiguration
{
    
    private let encoder = JSONEncoder()
    private let decoder = JSONDecoder()
    
    init?(as string: String) {
        super.init(as: string, using: nil, in: nil, type: .responsiveData)
    }
    
    func createCard(_ param: ScreenCardParameter, completionHandler: @escaping (CardData?)->Void)
    {
        do {
            let paramData = try encoder.encode(param)
            let url = try CardManagingURL.create.toURL()
            getRequestHandler(url: url) { request in
                self.session.uploadTask(with: request, from: paramData) { data, response, error in
                    guard let data = data else {
                        completionHandler(nil)
                        return
                    }
                    
                    completionHandler(try? self.decoder.decode(CardData.self, from: data))
                }.resume()
            }
        } catch {
            print(error)
            completionHandler(nil)
        }
    }
    
    func readCard(from objectId: String, completionHandler: @escaping (CardData?)->Void)
    {
        do {
            let queryItems = [URLQueryItem(name: "objectId", value: objectId)]
            var urlComp = try CardManagingURL.read.toURLComponent()
            urlComp.queryItems = queryItems
            
            if let url = urlComp.url {
                getRequestHandler(url: url) { request in
                    self.session.dataTask(with: request) { data, response, error in
                        guard let data = data else {
                            completionHandler(nil)
                            return
                        }
                        
                        completionHandler(try? self.decoder.decode(CardData.self, from: data))
                    }.resume()
                }
            } else {
                print("\(CardManagingError.CardURLError) urlComp: \(urlComp.string ?? ""), queryItems: \(queryItems)")
                throw CardManagingError.CardURLError
            }
            
        } catch {
            print(error)
            completionHandler(nil)
        }
    }
    
    func updateCard(_ data: CardData, completionHandler: @escaping (CardData?)->Void)
    {
        do {
            let paramData = try encoder.encode(data)
            let url = try CardManagingURL.update.toURL()
            
            getRequestHandler(url: url) { request in
                self.session.uploadTask(with: request, from: paramData) { data, response, error in
                    guard let data = data else {
                        completionHandler(nil)
                        return
                    }
                    
                    completionHandler(try? self.decoder.decode(CardData.self, from: data))
                }.resume()
            }
        } catch {
            print(error)
            completionHandler(nil)
        }
    }
    
    func deleteCard(from objectId: String, completionHandler: @escaping (CardData?)->Void)
    {
        do {
            let queryItems = [URLQueryItem(name: "objectId", value: objectId)]
            var urlComp = try CardManagingURL.delete.toURLComponent()
            urlComp.queryItems = queryItems
            
            if let url = urlComp.url {
                getRequestHandler(url: url) { request in
                    self.session.dataTask(with: request) { data, response, error in
                        guard let data = data else {
                            completionHandler(nil)
                            return
                        }
                        
                        completionHandler(try? self.decoder.decode(CardData.self, from: data))
                    }.resume()
                }
            } else {
                print("\(CardManagingError.CardURLError) urlComp: \(urlComp.string ?? ""), queryItems: \(queryItems)")
                throw CardManagingError.CardURLError
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
