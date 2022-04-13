import { fetchData, putData } from "../utils/utils.js";
import { serverURL } from "../constants/urlPath.js";

let todoListData;

export const getTodoListData = async () => {
  todoListData = await fetchData(`${serverURL}/todoList`);

  for (const listData of todoListData) {
    let value = listData.task;

    Object.defineProperty(listData, "task", {
      get: () => {
        return value;
      },
      set: ([id, title, newValue]) => {
        value = newValue;
        updateTodoListData([id, { title, task: newValue }]);
      },
    });
  }

  return todoListData;
};

export const updateTodoListData = ([id, updatedList]) => {
  putData(`${serverURL}/todoList/${id}`, updatedList);
};

const subscribers = {
  //   sidebar: []
  //   registration: []
};

const activation = {
  //   sidebar: false
  //   registration: false
  //   newTask: false
};

const updateListTask = (title, newTask) => {
  const list = todoListData.filter((e) => e.title === title)[0];
  if (newTask.id) {
    list.task[newTask.id - 1] = newTask;
  } else {
    newTask.id = list.task.length + 1;
    list.task.push(newTask);
  }
  list.task = [list.id, title, list.task];
};

const getTaskId = (taskList, taskTitle) => {
  let id = 1;
  for (const task of taskList) {
    if (task.title === taskTitle) {
      return id;
    }
    id++;
  }
  return id;
};

export const deleteListTask = (title, taskTitle) => {
  const list = todoListData.filter((e) => e.title === title)[0];
  const taskId = getTaskId(list.task, taskTitle);
  if (taskId === list.task.length) {
    list.task.pop();
    list.task = [list.id, title, list.task];
    return;
  }

  list.task = [
    list.id,
    title,
    list.task.filter((task) => {
      if (task.id === taskId) {
        return false;
      }

      if (task.id > taskId) {
        task.id--;
      }

      return true;
    }),
  ];
};

export const subscribe = (key, notify = null, defaultValue = false) => {
  if (activation[key] === undefined) {
    activation[key] = defaultValue;
    let value = activation[key];

    Object.defineProperty(activation, key, {
      get: () => {
        return value;
      },

      set: ([newValue, title, newTask]) => {
        value = newValue;
        subscribers[key].forEach((notify) => {
          notify(activation[key], title, newTask);
        });

        if (!newTask) return;
        updateListTask(title, newTask);
      },
    });
  }

  subscribers[key] = subscribers[key] || [];
  notify && subscribers[key].push(notify);
};

export const update = (key, title = null, newTask = null) => {
  if (newTask) return (activation[key] = [activation[key] + 1, title, newTask]);
  return (activation[key] = [!activation[key], title]);
};
