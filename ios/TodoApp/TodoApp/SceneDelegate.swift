//
//  SceneDelegate.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/04.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let scene = (scene as? UIWindowScene) else { return }
        
        self.window = UIWindow(windowScene: scene)
        
        let todoContainer = UIStoryboard(name: "TodoListContainerViewController", bundle: nil).instantiateInitialViewController() as? TodoListContainerViewController
        
        // TODO: Column 데이터 Fetching
        // TODO: 컨테이너에 TodoListViewController 주입하기

        self.window?.rootViewController = todoContainer
        self.window?.makeKeyAndVisible()
        
    }
}

