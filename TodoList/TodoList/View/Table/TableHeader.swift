//
//  TableHeader.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/07.
//

import UIKit

final class TableHeader: UITableViewHeaderFooterView{
    let titleLabel = UILabel()
    let numberLabel = UILabel()
    let plusButton = UIButton()
    var delegate: TableHeaderDelegate?
    
    override init(reuseIdentifier: String?){
        super.init(reuseIdentifier: reuseIdentifier)
        self.layer.backgroundColor = UIColor(red: 0.961, green: 0.961, blue: 0.969, alpha: 1).cgColor
        setAttributes()
    }
    
    required init?(coder: NSCoder){
        super.init(coder: coder)
        self.layer.backgroundColor = UIColor(red: 0.961, green: 0.961, blue: 0.969, alpha: 1).cgColor
        setAttributes()
    }
}

private extension TableHeader{
    func setAttributes(){
        configureTitleLabel()
        configureNumberLabel()
        configurePlusButton()
        setConstraints()
    }
    
    func configureTitleLabel(){
        titleLabel.textColor = UIColor(red: 0.004, green: 0.004, blue: 0.004, alpha: 1)
        titleLabel.font = UIFont(name: "NotoSansKR-Bold", size: 18)
        titleLabel.textAlignment = .center
        
        self.addSubview(titleLabel)
    }
    
    func configureNumberLabel(){
        numberLabel.textColor = UIColor(red: 0.004, green: 0.004, blue: 0.004, alpha: 1)
        numberLabel.font = UIFont(name: "NotoSansKR-Bold", size: 14)
        numberLabel.textAlignment = .center
        numberLabel.clipsToBounds = true
        numberLabel.layer.cornerRadius = 13
        numberLabel.layer.backgroundColor = UIColor(red: 0.741, green: 0.741, blue: 0.741, alpha: 1).cgColor
        
        self.addSubview(numberLabel)
    }
    
    func configurePlusButton(){
        plusButton.setImage(UIImage(named: "plus")?.withRenderingMode(.alwaysTemplate), for: .normal)
        plusButton.tintColor = UIColor(red: 0.741, green: 0.741, blue: 0.741, alpha: 1)
        
        self.addSubview(plusButton)
        plusButton.addTarget(self, action: #selector(touchedPlusButton(_:)), for: .touchUpInside)
    }
    
    func setConstraints(){
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        titleLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 7).isActive = true
        titleLabel.topAnchor.constraint(equalTo: self.topAnchor).isActive = true
        titleLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor).isActive = true
        
        numberLabel.translatesAutoresizingMaskIntoConstraints = false
        numberLabel.leadingAnchor.constraint(equalTo: self.titleLabel.trailingAnchor, constant: 8).isActive = true
        numberLabel.widthAnchor.constraint(equalToConstant: 26).isActive = true
        numberLabel.topAnchor.constraint(equalTo: self.topAnchor).isActive = true
        numberLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor).isActive = true
        
        plusButton.translatesAutoresizingMaskIntoConstraints = false
        plusButton.leadingAnchor.constraint(greaterThanOrEqualTo: self.numberLabel.trailingAnchor, constant: 50).isActive = true
        plusButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant:  -7).isActive = true
        plusButton.heightAnchor.constraint(equalToConstant: 24).isActive = true
        plusButton.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
    }
    
    @objc
    func touchedPlusButton(_ button: UIButton){
        self.delegate?.cardWillCreated(at: titleLabel.text ?? "해야할 일")
    }
}
