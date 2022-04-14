//
//  EditCardViewDelegate.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/12.
//

protocol EditCardViewDelegate {
    func didTapConfirmButton(editViewInfo:EditViewInputInfo )
    func didTapCancelButton()
    func textFieldDidEndEditing()
}
