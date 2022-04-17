//
//  PopUpContentsTextViewDelegate.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/12.
//

import UIKit

class PopUpContentsTextViewDelegate: NSObject, UITextViewDelegate {
    
    func textViewDidBeginEditing(_ textView: UITextView) {
        if textView.textColor == UIColor(red: 130/255, green: 130/255, blue: 130/255, alpha: 1.0) {
            textView.text = ""
            textView.textColor = .black
        }
    }
    
    func textViewDidEndEditing(_ textView: UITextView) {
        if textView.text.isEmpty {
            textView.text = Constant.PopUpViewText.contentsTextViewPlaceholder
            textView.textColor = UIColor(red: 130/255, green: 130/255, blue: 130/255, alpha: 1.0)
        }
    }
    
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        guard let str = textView.text else { return true }
        let newLength = str.count + text.count - range.length
        return newLength <= 500
    }
}
