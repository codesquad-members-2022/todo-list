package com.example.todolist.data

class TodoRepository {

    private var todoTaskList = mutableListOf<Task>(
        Task("sam", 0, "todo", "라이너스 화장실감", "라이너스 화장실갔다. 질문 받는다.", 0),
        Task("sam", 1, "todo", "라이너스 옴", "라이너스 왔다. 질문 받는다.", 1),
        Task("sam", 2, "todo", "뷰모델 만들기", "뷰모델 만들었다. 질문 받는다.", 2),
        Task("sam", 3, "todo", "어뎁터 만들기", "어뎁터다. 질문 받는다.", 3),
        Task("sam", 4, "todo", "테스트 만들기", "테스트다. 질문 받는다.", 4),
        Task("sam", 5, "todo", "알고리즘 풀기", "알고리즘다. 질문 받는다.", 5),
        Task("sam", 6, "todo", "청소하기", "청소다. 질문 받는다.", 6),
        Task("sam", 7, "todo", "밥 먹기", "밥. 질문 받는다.", 7)
    )

    fun getTaskList(): List<Task> {
        todoTaskList.sortByDescending { it.order }
        return todoTaskList
    }

    private var todoNum = todoTaskList.size

    fun testAdd(title: String, content: String) {
        todoTaskList.add(Task("sam", todoNum, "todo", title, content, todoNum))
        todoNum++
    }

    fun setTaskList(task: Task) {

    }

    fun deleteTodoTask(task: Task) {
        todoTaskList.remove(task)
    }

    fun dropTodoTask(droppedTask: Task, targetedTask: Task) {
        droppedTask.order = targetedTask.order - 1
        todoTaskList.add(droppedTask)
        todoTaskList.filter { it.order <= droppedTask.order && it != droppedTask }.forEach { it.order-- }
    }

    private var onGoingTaskList = mutableListOf<Task>(
        Task("sam", 1, "ongoing", "퍼니 옴", "퍼니라이너스 왔다. 질문 받는다.", 1),
        Task("sam", 2, "ongoing", "퍼니 뷰모델 만들기", "퍼니뷰모델 만들었다. 질문 받는다.", 2),
        Task("sam", 3, "ongoing", "퍼니 어뎁터 만들기", "퍼니어뎁터다. 질문 받는다.", 3),
        Task("sam", 4, "ongoing", "퍼니 테스트 만들기", "퍼니테스트다. 질문 받는다.", 4),
        Task("sam", 5, "ongoing", "퍼니 알고리즘 풀기", "퍼니알고리즘다. 질문 받는다.", 5),
        Task("sam", 6, "ongoing", "퍼니 청소하기", "퍼니청소다. 질문 받는다.", 6),
        Task("sam", 7, "ongoing", "퍼니 밥 먹기", "퍼니밥. 질문 받는다.", 7)
    )

    fun getOnGoingTaskList(): List<Task> {
        onGoingTaskList.sortByDescending { it.order }
        return onGoingTaskList
    }

    private var onGoingNum = onGoingTaskList.size

    fun testOnGoingAdd(title: String, content: String) {
        onGoingTaskList.add(Task("sam", onGoingNum, "todo", title, content, onGoingNum))
        onGoingNum++
    }

    fun setOnGoingTaskList(task: Task) {

    }

    fun deleteOnGoingTask(task: Task) {
        onGoingTaskList.remove(task)
    }

    fun dropOngoingTask(droppedTask: Task, targetedTask: Task) {
        droppedTask.order = targetedTask.order - 1
        onGoingTaskList.add(droppedTask)
        onGoingTaskList.filter { it.order <= droppedTask.order && it != droppedTask }.forEach { it.order-- }
    }

    private var completeTaskList = mutableListOf<Task>(
        Task("sam", 1, "complete", "퍼니 옴", "퍼니라이너스 왔다. 질문 받는다.", 1),
        Task("sam", 2, "complete", "퍼니 뷰모델 만들기", "퍼니뷰모델 만들었다. 질문 받는다.", 2),
        Task("sam", 3, "complete", "퍼니 어뎁터 만들기", "퍼니어뎁터다. 질문 받는다.", 3),
        Task("sam", 4, "complete", "퍼니 테스트 만들기", "퍼니테스트다. 질문 받는다.", 4),
        Task("sam", 5, "complete", "퍼니 알고리즘 풀기", "퍼니알고리즘다. 질문 받는다.", 5),
        Task("sam", 6, "complete", "퍼니 청소하기", "퍼니청소다. 질문 받는다.", 6),
        Task("sam", 7, "complete", "퍼니 밥 먹기", "퍼니밥. 질문 받는다.", 7)
    )

    fun getCompleteTaskList(): List<Task> {
        completeTaskList.sortByDescending { it.order }
        return completeTaskList
    }

    private var completeNum = completeTaskList.size

    fun testCompletedAdd(title: String, content: String) {
        completeTaskList.add(Task("sam", completeNum, "todo", title, content, completeNum))
        completeNum++
    }

    fun setCompletedTaskList(task: Task) {

    }

    fun deleteCompletedTask(task: Task) {
        completeTaskList.remove(task)
    }

    fun dropCompletedTask(droppedTask: Task, targetedTask: Task) {
        droppedTask.order = targetedTask.order - 1
        completeTaskList.add(droppedTask)
        completeTaskList.filter { it.order <= droppedTask.order && it != droppedTask }.forEach { it.order-- }
    }

}