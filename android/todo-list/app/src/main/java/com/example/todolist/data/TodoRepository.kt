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

    fun dropTodoTask(droppedTask: Task, targetedTask: Task) {
        droppedTask.order = targetedTask.order - 1
        todoTaskList.add(droppedTask)
        todoTaskList.filter { it.order <= droppedTask.order && it != droppedTask }.forEach { it.order-- }
    }


}