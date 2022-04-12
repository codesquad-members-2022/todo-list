import Foundation

struct URLManager {
    static func post(with taskCard: RequestCardData) {
        guard let uploadData = try? JSONEncoder().encode(taskCard) else {return}
        guard let url = URL(string: API.postURL) else {return}
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        URLSession.shared.uploadTask(with: request, from: uploadData){(data, response, error) in
            guard error == nil else {return}
            guard let response = response as? HTTPURLResponse else {return}
            switch response.statusCode {
            case APIResponse.alreadyExist:
                print("409")
            case APIResponse.itemCreated:
                print("201")
            case APIResponse.badRequest:
                print("400")
            default:
                break
            }
            guard let data = data else {return}
            if let result = try? JSONDecoder().decode(TaskCard.self, from: data) {
                NotificationCenter.default.post(name: .postCardData, object: result)
            }

        }.resume()
    }
    
    static func getTasks() {
        guard let url = URL(string: API.getTodosURL) else {return}
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        URLSession.shared.dataTask(with: request) {(data, response, error) in
            guard error == nil else {return}
            guard let response = response as? HTTPURLResponse else {return}
            switch response.statusCode {
            case APIResponse.goodRequest:
                print("200")
            case APIResponse.badRequest:
                print("400")
            default:
                break
            }
            guard let data = data else {return}
            if let result = try? JSONDecoder().decode(TaskBoard.self, from: data) {
                NotificationCenter.default.post(name: .getTaskBoardData, object: result)
            }
        }.resume()
    }

}

