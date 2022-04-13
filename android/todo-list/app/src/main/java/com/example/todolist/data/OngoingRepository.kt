package com.example.todolist.data

class OngoingRepository {

    private var todoTaskList = mutableListOf<Task>(
        Task("sam", 1, "ongoing", "퍼니 옴", "퍼니라이너스 왔다. 질문 받는다.", 1),
        Task("sam", 2, "ongoing", "퍼니 뷰모델 만들기", "퍼니뷰모델 만들었다. 질문 받는다.", 2),
        Task("sam", 3, "ongoing", "퍼니 어뎁터 만들기", "퍼니어뎁터다. 질문 받는다.", 3),
        Task("sam", 4, "ongoing", "퍼니 테스트 만들기", "퍼니테스트다. 질문 받는다.", 4),
        Task("sam", 5, "ongoing", "퍼니 알고리즘 풀기", "퍼니알고리즘다. 질문 받는다.", 5),
        Task("sam", 6, "ongoing", "퍼니 청소하기", "퍼니청소다. 질문 받는다.", 6),
        Task("sam", 7, "ongoing", "퍼니 밥 먹기", "퍼니밥. 질문 받는다.", 7)
    )

    fun getTaskList(): List<Task> {
        todoTaskList.sortByDescending { it.order }
        return todoTaskList
    }

    private var num = todoTaskList.size

    fun testAdd(title: String, content: String) {
        todoTaskList.add(Task("sam", num, "todo", title, content, num))
        num++
    }

    fun setTaskList(task: Task) {

    }

    fun deleteTask(task: Task) {
        todoTaskList.remove(task)
    }

    fun dropOngoingTask(droppedTask: Task, targetedTask: Task) {
        droppedTask.order = targetedTask.order - 1
        todoTaskList.add(droppedTask)
        todoTaskList.filter { it.order <= droppedTask.order && it != droppedTask }.forEach { it.order-- }
    }


}