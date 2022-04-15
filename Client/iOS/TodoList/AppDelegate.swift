//
//  AppDelegate.swift
//  TodoList
//
//  Created by jsj on 2022/04/04.
//

import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    static let middleWare = CardDataMiddleWare {
        DebugDataTask(api: Team13API())
    }
    
}

