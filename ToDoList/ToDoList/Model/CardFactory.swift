import Foundation

struct CardFactory {
    private static let defaultContentSet = [["GitHub 공부하기", "add, commit, push"],
                                            ["블로그에 포스팅할 것", "* GitHub 공부내용\n* 모던 자바스크립트 1장 공부내용"],
                                            ["HTML/CSS 공부하기", "input 태그 실습"]]
    
    static func makeRandomCard(in listName: String) -> Card {
        let randomIndex = Int.random(in: 0..<defaultContentSet.count)
        return Card(title: defaultContentSet[randomIndex][0], body: defaultContentSet[randomIndex][1], caption: .iOS, listName: listName)
    }
}
