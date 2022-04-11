//
//  HistoryView.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/11.
//

import Foundation
import UIKit

class HistoryView: UIView{
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setUI()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUI()
    }
    
    private func setUI(){
        backgroundColor = .yellow
    }
    
}
