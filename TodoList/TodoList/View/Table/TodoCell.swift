//
//  TodoCell.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/05.
//

import UIKit

final class TodoCell: UITableViewCell{
    private var backView: UIView!
    private var title: UILabel!
    private var contents: UILabel!
    private var author: UILabel!
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        self.backgroundColor = UIColor(red: 0.961, green: 0.961, blue: 0.969, alpha: 1)
        setComponents()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.backgroundColor = UIColor(red: 0.961, green: 0.961, blue: 0.969, alpha: 1)
        setComponents()
    }
    
    func setLabelText(title: String, contents: String){
        self.title.text = title
        self.contents.text = contents
    }
}

private extension TodoCell{
    func setComponents() {
        configureBackView()
        configureTitleLabel()
        configureContentLabel()
        configureAuthor()
        configureAutoLayout()
    }
    
    func configureBackView() {
        backView = UIView()
        backView.backgroundColor = .white
        backView.layer.cornerRadius = 10
        
        self.contentView.addSubview(backView)
    }

    func configureTitleLabel(){
        title = UILabel()
        title.textColor = UIColor(red: 0.004, green: 0.004, blue: 0.004, alpha: 1)
        title.font = UIFont(name: "NotoSansKR-Bold", size: 16)
        
        self.backView.addSubview(title)
    }
    
    func configureContentLabel(){
        contents = UILabel()
        contents.numberOfLines = 3
        contents.textColor = UIColor(red: 0.004, green: 0.004, blue: 0.004, alpha: 1)
        contents.font = UIFont(name: "NotoSansKR-Regular", size: 14)
        
        self.backView.addSubview(contents)
    }
    
    func configureAuthor(){
        author = UILabel()
        author.text = "author by iOS"
        author.textColor = UIColor(red: 0.741, green: 0.741, blue: 0.741, alpha: 1)
        author.font = UIFont(name: "NotoSansKR-Regular", size: 12)
        
        self.backView.addSubview(author)
    }
    
    func configureAutoLayout(){
        backView.translatesAutoresizingMaskIntoConstraints = false
        backView.topAnchor.constraint(equalTo: self.contentView.topAnchor).isActive = true
        backView.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor).isActive = true
        backView.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor).isActive = true
        backView.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor).isActive = true
        
        title.translatesAutoresizingMaskIntoConstraints = false
        title.topAnchor.constraint(equalTo: self.backView.topAnchor, constant: 16).isActive = true
        title.leadingAnchor.constraint(equalTo: self.backView.leadingAnchor, constant: 16).isActive = true
        title.trailingAnchor.constraint(equalTo: self.backView.trailingAnchor, constant: -16).isActive = true
        title.heightAnchor.constraint(equalToConstant: 23).isActive = true
        
        author.translatesAutoresizingMaskIntoConstraints = false
        author.bottomAnchor.constraint(equalTo: self.backView.bottomAnchor, constant: -16).isActive = true
        author.leadingAnchor.constraint(equalTo: self.backView.leadingAnchor, constant: 16).isActive = true
        author.trailingAnchor.constraint(equalTo: self.backView.trailingAnchor, constant: -16).isActive = true
        author.heightAnchor.constraint(equalToConstant: 17).isActive = true
        
        contents.translatesAutoresizingMaskIntoConstraints = false
        contents.topAnchor.constraint(equalTo: self.title.bottomAnchor, constant: 8).isActive = true
        contents.bottomAnchor.constraint(equalTo: self.author.topAnchor, constant: -8).isActive = true
        contents.leadingAnchor.constraint(equalTo: self.backView.leadingAnchor, constant: 16).isActive = true
        contents.trailingAnchor.constraint(equalTo: self.backView.trailingAnchor, constant: -16).isActive = true
    }
}
