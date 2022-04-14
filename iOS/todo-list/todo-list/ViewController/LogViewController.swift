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
    
    let logColumnViewModel = [LogViewModel(logManager: LogManager())]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        activityTableView.reloadDataAndKeepOffset()
        setupLogTableViewCell()
        setupViewModel()
    }
    
    private func setupLogTableViewCell() {
        activityTableView.register(LogTableViewCell.self, forCellReuseIdentifier: LogTableViewCell.identifier)
    }
    
    private func setupViewModel() {
        logColumnViewModel.enumerated().forEach { [weak self] (index, viewModel) in
            viewModel.list.bind { logVMs in
                DispatchQueue.main.async {
                    self?.activityTableView.reloadData()
                }
            }
            viewModel.load()
        }
    }
    
}

//MARK: - UITableViewDataSource에 관련된 Method
extension LogViewController{
    
    // TableView 행의 개수 반환 - return 값 변경 예정
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return logColumnViewModel.count
    }
    
    // TableView 각 셀에 대한 설정
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LogTableViewCell.identifier) as? LogTableViewCell,
              let cellVM = logColumnViewModel[indexPath.section][indexPath.row] else {
                  return UITableViewCell()
              }
        cell.logConfigure(with: cellVM)
        cell.imageView?.image = UIImage(named: "Party Face Emoji")
        
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
