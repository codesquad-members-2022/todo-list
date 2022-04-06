//
//  RequestCardsInBoardSession.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

/// 화면 내 카드를 업데이트하는 작업을 수행한다.
class CardsUpdateDataTask: CardHTTPRequest
{
    
    private let encoder = JSONEncoder()
    private let decoder = JSONDecoder()
    
    init?(as string: String) {
        super.init(as: string, using: nil, in: nil, type: .responsiveData)
    }
    
    /// 사용자가 입력한 카드의 내용을 토대로 서버에 카드 생성을 요청합니다.
    ///
    /// 현재 카드 생성 후 생성된 카드를 response 받을 수 있는지 부분 합의되지 않았지만,
    /// 생성된 카드를 response 받는다는 가정 하에 함수를 생성하였습니다.
    /// - param: 카드를 생성하는 데에 필요한 데이터를 가진 구조체입니다.
    /// - completionHandler: 카드 생성 API 요청 후 실행되는 클로저입니다.
    func createCard(_ param: ScreenCardParameter, completionHandler: @escaping (CardData?)->Void)
    {
        do {
            doPostRequest(url: try CardManagingURL.create.toURL(), try encoder.encode(param)) { data in
                
                guard let data = data else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(try? self.decoder.decode(CardData.self, from: data))
            }
        } catch {
            print(error)
            completionHandler(nil)
        }
    }
    
    /// 특정 키의 카드 정보를 요청합니다.
    ///
    /// - objectId: 요청하려는 카드의 키 값입니다.
    /// - completionHandler: 카드 데이터 불러오기 API 요청 후 실행되는 클로저입니다.
    func readCard(from objectId: String, completionHandler: @escaping (CardData?)->Void)
    {
        do {
            doGetRequest(url: try CardManagingURL.read.toURL(), parameter: ["objectId": objectId]) { data in
                
                guard let data = data else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(try? self.decoder.decode(CardData.self, from: data))
            }
        } catch {
            print(error)
            completionHandler(nil)
        }
    }
    
    /// 카드의 데이터를 전달하여 서버에 업데이트 요청합니다.
    ///
    /// 현재 카드 생성 후 생성된 카드를 response 받을 수 있는지 부분 합의되지 않았지만,
    /// 생성된 카드를 response 받는다는 가정 하에 함수를 생성하였습니다.
    /// - paramData: 업데이트 하려는 정보가 담긴 CardData 구조체 입니다.
    /// - completionHandler: 카드 업데이트 API 요청 후 실행되는 클로저입니다.
    func updateCard(_ paramData: CardData, completionHandler: @escaping (CardData?)->Void)
    {
        do {
            doPostRequest(url: try CardManagingURL.update.toURL(), try encoder.encode(paramData)) { data in
                
                guard let data = data else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(try? self.decoder.decode(CardData.self, from: data))
            }
        } catch {
            print(error)
            completionHandler(nil)
        }
    }
    
    /// 카드의 데이터를 전달하여 서버에 삭제를 요청합니다.
    ///
    /// 현재 카드 생성 후 생성된 카드를 response 받을 수 있는지 부분 합의되지 않았지만,
    /// 생성된 카드를 response 받는다는 가정 하에 함수를 생성하였습니다.
    /// - objectId: 삭제하려는 카드의 키 값입니다.
    /// - completionHandler: 카드 삭제 API 요청 후 실행되는 클로저입니다.
    func deleteCard(from objectId: String, completionHandler: @escaping (CardData?)->Void)
    {
        do {
            doGetRequest(url: try CardManagingURL.delete.toURL(), parameter: ["objectId":objectId]) { data in
                
                guard let data = data else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(try? self.decoder.decode(CardData.self, from: data))
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
