//
//  ActivityRecoderController.swift
//  todo-list
//
//  Created by Jason on 2022/04/05.
//

import Foundation
import UIKit

class LogViewController: UITableViewController {
    @IBOutlet weak var activityTableView: UITableView!
    
    //let logColumnViewModel = LogViewModel()
    
    let dumyData = ["HTML/CSS공부하기를 해야할 일에서 하고있는 일로 이동하였습니다.",
                    "해야할 일에 HTML/CSS 공부하기를 등록하였습니다.",
                    "해야할 일에 블로글에 포스팅할 것을 등록하였습니다."]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        activityTableView.reloadDataAndKeepOffset()
        
    }
    
}

//MARK: - UITableViewDataSource에 관련된 Method
extension LogViewController{
    
    // TableView 행의 개수 반환 - return 값 변경 예정
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 0
    }
    
    // TableView 각 셀에 대한 설정
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "Active") else { return UITableViewCell() }
        
        
        return cell
    }
    
}

//MARK: - UITableView 스크롤링 관련 Method
extension UITableView {
    func reloadDataAndKeepOffset() {
        
        // 스크롤링 멈추기
        setContentOffset(contentOffset, animated: false)
        
        // 데이터 추가 전 컨텐트 사이즈
        let beforeContentSize = contentSize
        reloadData()
        layoutIfNeeded()
        
        // 데이터 추가 후 컨텐트 사이즈
        let afterContentSize = contentSize
        
        // 데이터가 변경되고 리로드 되고나서 컨텐트 옵셋 계산
        let calculatednewOffset = CGPoint(
            x: contentOffset.x + (afterContentSize.width - beforeContentSize.width),
            y: contentOffset.y + (afterContentSize.height - beforeContentSize.height)
        )
        
        // 변경된 옵셋 설정
        setContentOffset(calculatednewOffset, animated: false)
        
    }
    
}
