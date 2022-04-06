//
//  MockNetworkRepository.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import Combine

class MockNetworkRepository<Target: BaseTarget> {
    private let provider = URLSessionProvider(session: MockURLSession(isRequestSuccess: true))
    
    func request<T: Decodable>(_ target: Target) -> AnyPublisher<T, SessionError> {
        var url = target.baseURL
        url = url.appendingPathComponent(target.path)
        
        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = target.method

        if let param = target.parameter,
           let body = try? JSONSerialization.data(withJSONObject: param, options: .init()) {
            urlRequest.httpBody = body
        }
        
        return Future<T, SessionError> { promise in
            self.provider.dataTask(request: urlRequest) { result in
                switch result {
                case .success(let data):
                    guard let dto = try? JSONDecoder().decode(T.self, from: data) else {
                        promise(.failure(.pasingError))
                        return
                    }
                    promise(.success(dto))
                case .failure(let error):
                    promise(.failure(error))
                }
            }
        }.eraseToAnyPublisher()
    }
}

struct MockData {
    let successData: Data = {
        "{\"result\":\"OK\", \"status\":\"200\"}".data(using: .utf8)!
    }()
    
    let failureData: Data = {
        "{\"result\":\"id aready exist\", \"status\":\"404\"}".data(using: .utf8)!
    }()
}

class MockURLSessionDataTask: URLSessionDataTask {
    var resumeDidCall: () -> Void = {}

    override func resume() {
        resumeDidCall()
    }
}

class MockURLSession: URLSessionProtocol {
    
    var isRequestSuccess: Bool
    var sessionDataTask: MockURLSessionDataTask?
    
    init(isRequestSuccess: Bool = true) {
        self.isRequestSuccess = isRequestSuccess
    }
    
    func dataTask(with request: URLRequest, completionHandler: @escaping (Data?, URLResponse?, Error?) -> Void) -> URLSessionDataTask {
        let successResponse = HTTPURLResponse(url: request.url!, statusCode: 200, httpVersion: "2", headerFields: nil)
        let failureResponse = HTTPURLResponse(url: request.url!, statusCode: 402, httpVersion: "2", headerFields: nil)
        
        let sessionDataTask = MockURLSessionDataTask()
        
        if isRequestSuccess {
            sessionDataTask.resumeDidCall = {
                completionHandler(MockData().successData, successResponse, nil)
            }
        } else {
            sessionDataTask.resumeDidCall = {
                completionHandler(MockData().failureData, failureResponse, nil)
            }
        }
        
        self.sessionDataTask = sessionDataTask
        return sessionDataTask
    }
}
