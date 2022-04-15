package com.example.todo.ui.toDo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.ActionLog
import com.example.todo.model.ProgressType
import com.example.todo.model.TodoItem
import com.example.todo.respository.ActionLogRepository
import com.example.todo.respository.ToDoRepository
import kotlinx.coroutines.launch

class ToDoViewModel(
    private val actionLogRepository: ActionLogRepository,
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    private val _todoList = MutableLiveData<List<TodoItem>>()
    private val _inProgressList = MutableLiveData<List<TodoItem>>()
    private val _doneList = MutableLiveData<List<TodoItem>>()
    private val _actionList = MutableLiveData<List<ActionLog>>()

    val todoList: LiveData<List<TodoItem>> = _todoList
    val inProgressList: LiveData<List<TodoItem>> = _inProgressList
    val doneList: LiveData<List<TodoItem>> = _doneList
    val actionList: LiveData<List<ActionLog>> = _actionList

    init {
        loadActionLog()
        loadTodoList()
    }

    private fun loadTodoList() {
        viewModelScope.launch {
            val totalList = toDoRepository.getTodoItems()
            val tempTodoList = mutableListOf<TodoItem>()
            val progressList = mutableListOf<TodoItem>()
            val doneList = mutableListOf<TodoItem>()
            totalList?.forEach {
                if (it.type == ProgressType.TO_DO) tempTodoList.add(it)
                else if (it.type == ProgressType.IN_PROGRESS) progressList.add(it)
                if (it.type == ProgressType.DONE) doneList.add(it)
            }
            _todoList.value = reOrganize(tempTodoList)
            _inProgressList.value = reOrganize(progressList)
            _doneList.value = reOrganize(doneList)
        loadActionLog()
        }

    }


    private fun reOrganize(list: List<TodoItem>): List<TodoItem> {

        val newList = mutableListOf<TodoItem>()
        if (list.size > 1) {
            val header = list.find { it.next == 0 }
            header?.let { newList.add(header) }
            var statKey = header?.itemId ?: return list
            println(statKey)

            loop@ while (true) {
                val nextItem = findNextTodoItem(list, statKey)
                nextItem?.let {
                    statKey = nextItem.itemId!!
                    newList.add(nextItem)
                } ?: break
            }

//           // var count = list.size - 1
//            while (count > 0) {
//                statKey = findByNextId(statKey, list, newList)
//                count--
//            }
        } else return list
        return newList.toList()
    }

    private fun findNextTodoItem(list: List<TodoItem>, itemId: Int): TodoItem? {
        val item = list.find { it.next == itemId }
        return item
    }

    private fun findByNextId(
        nextId: Int,
        list: List<TodoItem>,
        resultList: MutableList<TodoItem>
    ): Int {
        val addItem = list.find { it.next == nextId }
        addItem?.let { resultList.add(it) }
        return addItem?.itemId!!
    }

    private fun loadActionLog() {
        viewModelScope.launch {
            val actionLogs = actionLogRepository.getActionLogs()
            actionLogs?.let { _actionList.value = actionLogs!! }
        }
    }

    fun addTodoItem(item: TodoItem) {
        viewModelScope.launch {
            _todoList.value = todoList.value?.let { toDoRepository.addToDoItem(it, item) }
        }
    }

    fun addInProgressItem(item: TodoItem) {
        viewModelScope.launch {
            _inProgressList.value =
                inProgressList.value?.let { toDoRepository.addToDoItem(it, item) }
        }
    }

    fun addDoneItem(item: TodoItem) {
        viewModelScope.launch {
            _doneList.value = doneList.value?.let { toDoRepository.addToDoItem(it, item) }
        }
    }

    private suspend fun deleteTodoItem(item: TodoItem) {
        _todoList.value = todoList.value?.let { toDoRepository.removeToDoItem(it, item) }
    }

    private suspend fun deleteInProgressItem(item: TodoItem) {
        _inProgressList.value =
            inProgressList.value?.let {
                toDoRepository.removeToDoItem(it, item)
            }
    }

    private suspend fun deleteDoneItem(item: TodoItem) {
        _doneList.value = doneList.value?.let { toDoRepository.removeToDoItem(it, item) }
    }

    fun moveToDone(item: TodoItem) {
        viewModelScope.launch {
            when (item.type) {
                ProgressType.IN_PROGRESS -> {
                    deleteInProgressItem(item)
                    item.type = ProgressType.DONE
                    addDoneItem(item)
                }
                ProgressType.DONE -> {
                    return@launch
                }
                else -> {
                    deleteTodoItem(item)
                    item.type = ProgressType.DONE
                    addDoneItem(item)
                }
            }
        }
    }

    fun deleteItem(cardItem: TodoItem) {
        viewModelScope.launch {
            when (cardItem.type) {
                ProgressType.TO_DO -> {
                    deleteTodoItem(cardItem)
                }
                ProgressType.IN_PROGRESS -> {
                    deleteInProgressItem(cardItem)
                }
                ProgressType.DONE -> {
                    deleteDoneItem(cardItem)
                }
            }
        }
    }

    fun updateTodoItem(item: TodoItem) {
        viewModelScope.launch {
            when (item.type) {
                ProgressType.TO_DO -> {
                    _todoList.value =
                        _todoList.value?.let { toDoRepository.updateToDoItem(it, item) }
                }
                ProgressType.IN_PROGRESS -> {
                    _inProgressList.value =
                        inProgressList.value?.let { toDoRepository.updateToDoItem(it, item) }
                }
                ProgressType.DONE -> {
                    _doneList.value =
                        _doneList.value?.let { toDoRepository.updateToDoItem(it, item) }
                }
            }
        }
    }

    fun moveTodoItem(itemId: Int, type: ProgressType, prevId: Int?, nextId: Int?) {
        viewModelScope.launch {
            val isSuccess = toDoRepository.moveTodoItem(itemId, type, prevId, nextId)
            // 재로드 필요
            if (isSuccess) loadTodoList()
        }
    }

}