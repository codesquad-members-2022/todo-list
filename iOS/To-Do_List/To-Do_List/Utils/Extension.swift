//
//  Extension.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/11.
//

import UIKit

extension String {
    func image(size:CGSize,color:UIColor,fontSize:CGFloat) -> UIImage? {
        UIGraphicsBeginImageContextWithOptions(size, false, 0)
        color.set()
        let rect = CGRect(origin: .zero, size: size)
        UIRectFill(CGRect(origin: .zero, size: size))
        (self as AnyObject).draw(in: rect, withAttributes: [.font: UIFont.systemFont(ofSize: fontSize)])
        let image = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        return image
    }
}
