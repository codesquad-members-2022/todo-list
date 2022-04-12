//
//  NetworkManager.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/07.
//


import Foundation

// API docs - https://hooria.herokuapp.com/swagger-ui/index.html#/

enum NetworkError: Error {
    case noData
    case decoding
    case encoding
    case delete(Error)
}

struct NetworkManager {
    let urlSession = URLSession.shared
    
    var components: URLComponents = {
        var components = URLComponents()
        components.scheme = "http"
        components.host = "15.164.250.0"
        components.port = 8080
        components.path = "/api/cards"
        return components
    }()
    
    var logComponents: URLComponents = {
        var logComponents = URLComponents()
        logComponents.scheme = "http"
        logComponents.host = "15.164.250.0"
        logComponents.port = 8080
        logComponents.path = "/api/activities"
        return logComponents
    }()
    
    let decoder = JSONDecoder().krISO8601
    let encoder = JSONEncoder().krISO8601
    
    func getAllTasks(then completion: @escaping (Result<[Task], NetworkError>) -> Void) {
        guard let urlString = components.string,
              let url = URL(string: urlString) else { return }
        
        urlSession.dataTask(with: url) { data, response, error in
            
            guard let data = data else {
                return completion(.failure(.noData))
            }
            
            guard let decoded = try? decoder.decode([Task].self, from: data) else {
                debugPrint(data.prettyPrintedJSONString!)
                return completion(.failure(.decoding))
            }
            
            completion(.success(decoded))
            
        }.resume()
        
    }
    
    func delete(id: Int, then completion: @escaping (Result<Int, NetworkError>) -> Void) {
        var components = components
        components.queryItems = [URLQueryItem(name: "id", value: String(id))]
        
        guard let urlString = components.string,
              let url = URL(string: urlString) else { return }
        
        var request = URLRequest(url: url)
        request.httpMethod = "DELETE"
        
        urlSession.dataTask(with: request) { data, response, error in
            if let error = error {
                return completion(.failure(.delete(error)))
            } else {
                return completion(.success(id))
            }
        }.resume()
    }
    
    func post<T: Codable>(item: T, then completion: @escaping (Result<T, NetworkError>) -> Void) {
        guard let body = try? encoder.encode(item) else {
            return completion(.failure(.encoding))
        }
        
        guard let urlString = components.string,
              let url = URL(string: urlString) else { return }
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.httpBody = body
<<<<<<< HEAD
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
=======
>>>>>>> 60d70e9 (🐛: ViewModel에서 nil이 넘어오는 버그를 Observable의 bind와 클로저 실행 분리로 해결)

        print("Body", String(data: body, encoding: .utf8)!)
        urlSession.dataTask(with: request) { data, response, error in
            guard let data = data else {
                return completion(.failure(.noData))
            }
            print("Response:", String(data: data, encoding: .utf8)!)
            guard let decoded = try? decoder.decode(T.self, from: data) else {
                return completion(.failure(.decoding))
            }
            
            completion(.success(decoded))
            
        }.resume()
    }
    
    func patch<T: Codable>(item: T, then completion: @escaping (Result<T, NetworkError>) -> Void) {
        guard let body = try? encoder.encode(item) else {
            return completion(.failure(.encoding))
        }
        
        guard let urlString = components.string,
              let url = URL(string: urlString) else { return }
        
        var request = URLRequest(url: url)
        request.httpMethod = "PATCH"
        request.httpBody = body
        
        urlSession.dataTask(with: request) { data, response, error in
            guard let data = data else {
                return completion(.failure(.noData))
            }
            
            guard let decoded = try? decoder.decode(T.self, from: data) else {
                return completion(.failure(.decoding))
            }
            
            completion(.success(decoded))
            
        }.resume()
    }
    

    func getAllLogs(then completion: @escaping (Result<[Log], NetworkError>) -> Void) {
        guard let urlString = components.string,
              let url = URL(string: urlString) else { return }
        
        urlSession.dataTask(with: url) { data, response, error in
            
            guard let data = data else {
                return completion(.failure(.noData))
            }

            guard let decoded = try? decoder.decode([Log].self, from: data) else {
                debugPrint(data.prettyPrintedJSONString!)
                return completion(.failure(.decoding))
            }
            
            completion(.success(decoded))
            
        }.resume()

    }
    
    func getAllDumyLogs(then completion: @escaping (Result <[Log], NetworkError>) -> Void) {
        let dummyLogJSONData: Data? = {
            guard let url = Bundle.main.url(forResource: "dummyLogResponse", withExtension: "json") else { return nil }
            guard let dummyJSONData = try? Data(contentsOf: url) else { return nil }
            return dummyJSONData
        }()
        
        guard let dummyLogJSONData = dummyLogJSONData else { return }
        
        guard let logs = try? self.decoder.decode([Log].self, from: dummyLogJSONData) else {
            return completion(.failure(.decoding))
        }
        
        completion(.success(logs))
        
    }
}
