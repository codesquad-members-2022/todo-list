//
//  ActivityRecoderController.swift
//  todo-list
//
//  Created by Jason on 2022/04/05.
//

import Foundation
import UIKit

class ActivityRecordController: UITableViewController {
    
    var tempArray = [String]()
    
    let dumyData = ["aaa", "bbb", "ccc"]
    
    @IBOutlet weak var activityTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
    }
    
}

//MARK: - TableView 관련 Method
extension ActivityRecordController {
    
    // 데이터가 앞에 추가되었을때
    fileprivate func prependData() {
        print("#fileID", "#function", "#line", "")
        self.activityTableView.reloadDataAndKeepOffset()
    }
    
    fileprivate func appendData() {
        // 데이터 추가
    }
    
    fileprivate func resetData() {
        print("#fileID", "#function", "#line", "")
    }
}


//MARK: - UITableViewDataSource에 관련된 Method
extension ActivityRecordController{
    
    // TableView 행의 개수 반환 - return 값 변경 예정
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.dumyData.count
    }
    
    // TableView 각 셀에 대한 설정
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "Active") else { return UITableViewCell() }
        
        var config = cell.defaultContentConfiguration()
        config.text = dumyData[indexPath.row]
        config.secondaryText = "test"
        config.image = UIImage(named: "pencil")
        
        cell.contentConfiguration = config
        
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
