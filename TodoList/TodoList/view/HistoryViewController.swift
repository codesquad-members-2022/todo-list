//
//  HistoryViewController.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/12.
//

import Foundation
import UIKit

class HistoryViewController: UIViewController{
    private lazy var historyView = HistoryView(frame: view.frame)
    private let historyBoard = HistoryBoard()
    private var historyViewBeforeConstraint: NSLayoutConstraint?
    private var historyViewAfterConstraint: NSLayoutConstraint?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setHistoryView()
    }
    
    private func setHistoryView(){
        historyView.actionDelegate = self
        historyBoard.historyBoardAction = self
        view = historyView
    }
    
    override func didMove(toParent parent: UIViewController?) {
        super.didMove(toParent: parent)
        setHistoryConstraint()
    }
    
    private func setHistoryConstraint(){
        view.translatesAutoresizingMaskIntoConstraints = false
        guard let superview = view.superview else {
            return
        }
        view.topAnchor.constraint(equalTo: superview.topAnchor).isActive = true
        view.bottomAnchor.constraint(equalTo: superview.bottomAnchor).isActive = true
        view.widthAnchor.constraint(equalToConstant: 300).isActive = true
        self.historyViewBeforeConstraint = view.leadingAnchor.constraint(equalTo: superview.trailingAnchor)
        self.historyViewAfterConstraint = view.trailingAnchor.constraint(equalTo: superview.trailingAnchor)
        historyViewBeforeConstraint?.isActive = true
    }
    
    private func historyAppearAnimate(){
        UIView.animate(withDuration: 0.3) {
            self.historyViewBeforeConstraint?.isActive = false
            self.historyViewAfterConstraint?.isActive = true
            self.view.superview?.layoutIfNeeded()
        }
    }
    
    private func historyDisappearAnimate(){
        UIView.animate(withDuration: 0.3) {
            self.historyViewAfterConstraint?.isActive = false
            self.historyViewBeforeConstraint?.isActive = true
            self.view.superview?.layoutIfNeeded()
        }
    }
    
    func historyButtonTappedAppear(){
        historyBoard.historyButtonTapped()
    }
}
extension HistoryViewController: HistoryViewAction{
    func closeButtonTapped() {
        historyBoard.historyButtonTapped()
    }
}
extension HistoryViewController: HistoryBoardDelegate{
    func historyViewHiddenChanged(_ hiddenState: HiddenState) {
        if hiddenState == .hidden{
            historyDisappearAnimate()
        }
        
        if hiddenState == .show{
            historyAppearAnimate()
        }
    }
}
