//
//  AppDelegate.swift
//  TodoList
//
//  Created by jsj on 2022/04/04.
//

import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    static let dataTask = DataTask(api: Team13API())
    static let middleWare = CardDataMiddleWare(persistenceProvider: BoardPersistenceProvider()) {
            dataTask
    }
    
    func applicationDidFinishLaunching(_ application: UIApplication) {
        // TODO:- 사용자 쿠키를 받아오지 못했을때 처리
        Self.dataTask?.fetchUser(completionHandler: { result in
            switch result {
            case .success:
                break
            case .failure:
                break
            }
        })
    }
}
