//
//  LogManager.swift
//  todo-list
//
//  Created by Jason on 2022/04/12.
//

import Foundation

// LogViewModel에 LogManager라는 얘가 뭘 넘겨야할까?
struct LogManager {
    
    private let networkManager = NetworkManager()
    
    func load(then completion: @escaping([Log]) -> Void) {
        networkManager.getAllDumyLogs { result in
            switch result {
            case .success(let data):
                completion(data)
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
}
