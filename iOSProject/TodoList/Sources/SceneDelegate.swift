//
//  SceneDelegate.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?
    
    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let scene = (scene as? UIWindowScene) else { return }
        
        window = UIWindow(windowScene: scene)
        window?.overrideUserInterfaceStyle = .light
//        window?.rootViewController
        window?.makeKeyAndVisible()
    }
}

