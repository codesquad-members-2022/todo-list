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
        
        ColumnRepository().fetchColumn { columns in
            DispatchQueue.main.async {
                let storyboard = UIStoryboard(name: "TodoListViewController", bundle: nil)
                
                let viewControllers: [TodoListViewController] = columns.compactMap({ column in
                    guard let todoListViewController = storyboard.instantiateInitialViewController() as? TodoListViewController else { return nil }
                    
                    let todoRepository = TodoRepository()
                    
                    todoListViewController.viewModel = TodoListViewModel(entity: column, repository: todoRepository)
                    
                    return todoListViewController
                })
                
                todoContainer?.viewControllers = viewControllers
            }
        }
     
        self.window?.rootViewController = todoContainer
        self.window?.makeKeyAndVisible()
    }
}

