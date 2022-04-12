//
//  AppDelegate.swift
//  TodoList
//
//  Created by jsj on 2022/04/04.
//

import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {

    static let middleware = CardDataMiddleWare()

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        AppDelegate.middleware.fetchAllCards()
        return true
    }
}

