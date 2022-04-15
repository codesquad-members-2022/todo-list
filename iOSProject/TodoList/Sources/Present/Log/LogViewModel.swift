//
//  logViewModel.swift
//  TodoList
//
//  Created by Joobang Lee on 2022/04/14.
//

import Foundation
import Combine

struct LogViewModelAction {
    let loadLog = PassthroughSubject<Void, Never>()
}

struct LogViewModelState {
    let loadedLog = PassthroughSubject<Void, Never>()
}

protocol LogViewModelBinding {
    var action: LogViewModelAction { get }
    var state: LogViewModelState { get }
}

protocol LogViewModelProperty {
    subscript(index: Int) -> ActivityLog? { get }
    var logCount: Int { get }
}

typealias LogViewModelProtocol = LogViewModelBinding & LogViewModelProperty

final class LogViewModel: LogViewModelBinding, LogViewModelProperty {
    private var cancellables = Set<AnyCancellable>()
    private let todoRepository: TodoRepository = TodoRepositoryImpl()
    
    private var logs = [ActivityLog]()
    
    subscript(index: Int) -> ActivityLog? {
        logs[index]
    }
    
    var logCount: Int {
        logs.count
    }
    
    let action = LogViewModelAction()
    let state = LogViewModelState()

    init() {
        let requestLoadLog = action.loadLog
            .map { index in self.todoRepository.loadLogs()}
            .switchToLatest()
            .share()
        
        requestLoadLog
            .compactMap{ $0.value }
            .sink { logs in
                self.logs = logs
                self.state.loadedLog.send()
            }.store(in: &cancellables)
    }
}
