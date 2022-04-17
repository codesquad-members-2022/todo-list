//
//  ColumnRepository.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/13.
//

import Foundation

protocol ColumnRepositoryProtocol {
    func fetchColumn(_ completion: @escaping([Column]) -> Void)
}

class ColumnRepository: ColumnRepositoryProtocol {
    func fetchColumn(_ completion: @escaping ([Column]) -> Void) {
        URLSession.shared.dataTask(with: URL(string: "/columns", relativeTo: API.baseURL)!) { data, response, error in
            guard let data = data, error == nil else {
                // TODO: response error handling
                return
            }
            
            guard let models = try? JSONDecoder().decode([Column].self, from: data) else { return }
            completion(models)
        }.resume()
    }
}
