import Foundation

struct LogFactory {
    private static let defaultContent = [["@sam", "HTML/CSS공부하기를 해야할 일에서 하고 있는 일로 이동하였습니다."], ["@sam", "HTML/CSS공부하기를 등록하였습니다."], ["@sam", "해야할 일에 블로그에 포스팅할 것을 등록하였습니다."], ["@sam", "해야할 일에 GitHub 공부하기를 등록하였습니다."]]
    
    static func makeRandomLog() -> Log {
        let randomIdx = Int.random(in: 0..<defaultContent.count)
        return Log(name: defaultContent[randomIdx][0], body: defaultContent[randomIdx][1])
    }
}
