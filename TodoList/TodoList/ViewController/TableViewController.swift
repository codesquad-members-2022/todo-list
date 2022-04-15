//
//  TableViewController.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/11.
//

import UIKit

final class TableViewController: UIViewController{
    enum CellIdentifier: String{
        case tableCell = "tableCell"
        
        func getRawValue() -> String{
            return self.rawValue
        }
    }
    
    private var sectionHeader = [TableHeader]()
    private var todoTable = [TodoTableView]()

    private var addCardViewController = AddCardViewController()

    let cardBoard: Board = Board.shared

    let todo = ["해야할 일", "하고있는 일", "끝난 일"]
    private var selectedSection: Int?

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didMove(toParent parent: UIViewController?) {
        super.didMove(toParent: parent)
        self.connectURL()
    }

    
    func setTableAttributes(cell: CollectionCell, index: Int){
        let header = configureSectionHeader(index: index)
        let table = configureTableView(index: index)
        configureLayout(cell: cell, header: header, tableView: table)
    }
}

private extension TableViewController{
    // 서버 통신 및 데이터 변환 작업
    func connectURL(){
        NotificationCenter.default.addObserver(self, selector: #selector(setCardData), name: NSNotification.Name(rawValue: "board"), object: cardBoard)
        NotificationCenter.default.addObserver(self, selector: #selector(createNewCard), name: NSNotification.Name(rawValue: "addCard"), object: cardBoard)
        self.cardBoard.getAndDivideCard()
    }
    
    @objc
    func setCardData(){
        DispatchQueue.main.async {
            self.todoTable.forEach{
                $0.reloadData()
            }
            
            for (index, header) in self.sectionHeader.enumerated(){
                guard let indexPath = BoardSubscriptIndex(rawValue: index) else{ return }
                
                header.numberLabel.text = String(self.cardBoard[indexPath].count)
            }
        }
    }
                                               
    @objc
    func createNewCard(){
        // 만든 카드를 테이블뷰 셀 추가 & reload
        DispatchQueue.main.async {
            guard let sectionNumber = self.selectedSection else { return }
            self.todoTable[sectionNumber].reloadData()
            
            guard let indexPath = BoardSubscriptIndex(rawValue: sectionNumber) else{ return }
            self.sectionHeader[sectionNumber].numberLabel.text = String(self.cardBoard[indexPath].count)
        }
    }
                                               
    // 내부 View layout 작업
    func configureSectionHeader(index: Int) -> TableHeader{
        let header = TableHeader()
        header.titleLabel.text = todo[index]
        header.tableHeaderID = index
        
        if let indexPath = BoardSubscriptIndex(rawValue: index){
            header.numberLabel.text = String(cardBoard[indexPath].count)
        } else{
            header.numberLabel.text = "0"
        }
        
        header.delegate = self
        sectionHeader.append(header)
        
        return header
    }
    
    func configureTableView(index: Int) -> TodoTableView{
        let tableView = TodoTableView()
        tableView.setTableViewId(number: index)
        tableView.estimatedRowHeight = 108
        tableView.rowHeight = UITableView.automaticDimension
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(TodoCell.classForCoder(), forCellReuseIdentifier: CellIdentifier.tableCell.getRawValue())
        
        todoTable.append(tableView)
        
        return tableView
    }
    
    func configureLayout(cell: CollectionCell, header: TableHeader, tableView: TodoTableView){
        cell.contentView.addSubview(header)
        header.translatesAutoresizingMaskIntoConstraints = false
        header.leadingAnchor.constraint(equalTo: cell.contentView.leadingAnchor).isActive = true
        header.trailingAnchor.constraint(equalTo: cell.contentView.trailingAnchor).isActive = true
        header.topAnchor.constraint(equalTo: cell.contentView.topAnchor, constant: 51).isActive = true
        header.heightAnchor.constraint(equalToConstant: 26).isActive = true
        
        cell.contentView.addSubview(tableView)
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.leadingAnchor.constraint(equalTo: cell.contentView.leadingAnchor).isActive = true
        tableView.trailingAnchor.constraint(equalTo: cell.contentView.trailingAnchor).isActive = true
        tableView.topAnchor.constraint(equalTo: header.bottomAnchor, constant: 16).isActive = true
        tableView.bottomAnchor.constraint(equalTo: cell.contentView.bottomAnchor).isActive = true
    }
    
    // 셀 클릭 시, 수정 화면 출력 기능
    func setModalPresent(){
        addCardViewController.modalPresentationStyle = .overCurrentContext
        addCardViewController.modalTransitionStyle = .crossDissolve
        self.present(addCardViewController, animated: true, completion: nil)
    }
}

extension TableViewController: UITableViewDataSource, UITableViewDelegate{
    // Footer 관련 메서드(셀 간격용)
    func numberOfSections(in tableView: UITableView) -> Int {
        guard let customTable = tableView as? TodoTableView, let indexPath = BoardSubscriptIndex(rawValue: customTable.tableViewId ?? 4) else { return 0 }
        let cards = cardBoard[indexPath]
        
        return cards.count
    }

    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 16
    }
    
    func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
        return nil
    }
    
    // cell 관련 메서드
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CellIdentifier.tableCell.getRawValue()) as? TodoCell, let customTable = tableView as? TodoTableView, let boardIndexPath = BoardSubscriptIndex(rawValue: customTable.tableViewId ?? 4) else { return UITableViewCell() }
        cell.selectionStyle = .none
        
        let cards = cardBoard[boardIndexPath]
        let cardData = cards[indexPath.section]
        cell.setLabelText(title: cardData.title, contents: cardData.content)
        
        return cell
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath){
        guard let customTable = tableView as? TodoTableView, let index = BoardSubscriptIndex(rawValue: customTable.tableViewId ?? 4) else { return }
        let cards = cardBoard[index]
        let title = cards[indexPath.section].title
        let body = cards[indexPath.section].content
        
        addCardViewController.setaddCardView(title: title, body: body)
        self.setModalPresent()

    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        guard let customTable = tableView as? TodoTableView, let index = BoardSubscriptIndex(rawValue: customTable.tableViewId ?? 4) else { return }

        if editingStyle == .delete{
            let card = cardBoard[index]
            let deleteId = card[indexPath.section].id
            Board.shared.deleteCard(deleteId, at: index)
            customTable.deleteSections([indexPath.section], with: .fade)
            
        }
    }
}

extension TableViewController: TableHeaderDelegate{
    func cardWillCreated(at section: Int){
        addCardViewController.sectionNumber = section
        addCardViewController.modalPresentationStyle = .overCurrentContext
        addCardViewController.modalTransitionStyle = .crossDissolve
        self.present(addCardViewController, animated: true, completion: nil)
        selectedSection = section
    }
}
