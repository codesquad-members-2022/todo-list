//
//  SessionCommonAttributes.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

/// 세션을 생성하는 데 필요한 기본 프로퍼티들을 저장하고 관리합니다.
///
/// - request: dataTask 요청할 때 필요한 구조체입니다.
/// - httpMethod: 요청하는 httpMethod. 변경하면 자동으로 request가 업데이트 됩니다.
/// - mimeType: 요청하는 mimeType. 변경하면 자동으로 request가 업데이트 됩니다.
class SessionCommonAttributes {
    
    // MARK: - Local Properties
    private var request: URLRequest!
    
    var urlString: String
    
    var httpMethod: HTTPMethod = .POST
    {
        didSet {
            self.request.httpMethod = httpMethod.rawValue
        }
    }
    var mimeType: MIMEType = .applicationJSON
    {
        didSet {
            self.request.setValue(MIMEType.applicationJSON.rawValue, forHTTPHeaderField: "Content-Type")
        }
    }
    
    // MARK: - Initializers
    init?(as string: String)
    {
        self.urlString = string
        if let url = URL(string: string) {
            reloadRequestURL(url)
        } else {
            return nil
        }
    }
    
    func getRequestHandler(url: URL, completionHandler: @escaping (URLRequest) -> Void) {
        reloadRequestURL(url)
        completionHandler(request)
    }
    
    func getCurrentRequestHandler(completionHandler: @escaping (URLRequest) -> Void) {
        completionHandler(request)
    }
    
    private func reloadRequestURL(_ url: URL) {
        request = URLRequest(url: url, cachePolicy: .useProtocolCachePolicy, timeoutInterval: 6.0)
        request.httpMethod = httpMethod.rawValue
        request.setValue(mimeType.rawValue, forHTTPHeaderField: "Content-Type")
    }
    
    // MARK: - HTTPMethod Types
    
    enum HTTPMethod: String
    {
        /// 엔티티 제출 시 사용. 서버 상태변화 등을 일으킵니다.
        case POST = "POST"
        /// 리소스 요청. 데이터를 받기만 합니다.
        case GET = "GET"
    }
    
    // MARK: - MIMEType Types
    
    enum MIMEType: String
    {
        case applicationOctetStream = "application/octet-stream"
        
        case applicationJSON = "application/json"
        
        case imageJPEG = "image/jpeg"
        case imagePNG = "image/png"
        case imageGIF = "image/gif"
        
        case textPlain = "text/plain"
    }
}
