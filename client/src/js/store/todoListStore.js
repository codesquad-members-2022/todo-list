import { fetchData, postData, putData } from "../utils/utils.js";
import { serverURL } from "../constants/urlPath.js";

let todoListData;
let historyData;

const getTodoListData = async () => {
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

const updateTodoListData = ([id, updatedList]) => {
  putData(`${serverURL}/todoList/${id}`, updatedList);
};

const getHistoryData = async () => {
  historyData = { list: await fetchData(`${serverURL}/history`) };
  let historyList = historyData.list;

  Object.defineProperty(historyData, "list", {
    get: () => {
      return historyList;
    },
    set: ([actionType, categories, { title }]) => {
      debugger;
      const newHistory = {
        action: actionType,
        category: categories,
        title: title,
        timeStamp: new Date().toString().slice(0, 33),
        id: historyList.length + 1,
      };

      historyList.push(newHistory);
      postHistoryData(newHistory);

      subscribers["history"].forEach((notify) => {
        notify(historyList);
      });
    },
  });

  return historyList;
};

const postHistoryData = (newHistory) => {
  postData(`${serverURL}/history`, newHistory);
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
  let actionType;

  if (newTask.id) {
    list.task[newTask.id - 1] = newTask;
    actionType = "변경";
  } else {
    newTask.id = list.task.length + 1;
    list.task.push(newTask);
    actionType = "등록";
  }
  list.task = [list.id, title, list.task];
  historyData.list = [actionType, [title], newTask];
};

const deleteListTask = (title, taskId) => {
  const list = todoListData.filter((e) => e.title === title)[0];
  list.task = [
    list.id,
    title,
    list.task.filter((task) => {
      if (task.id === taskId) {
        historyData.list = ["삭제", [title], task];
        return false;
      }

      if (task.id > taskId) {
        task.id--;
      }

      return true;
    }),
  ];
};

const subscribe = (key, notify = null, defaultValue = false) => {
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

const update = (key, title = null, newTask = null) => {
  if (key === "newTask") return (activation[key] = [activation[key] + newTask ? 1 : -1, title, newTask]);
  return (activation[key] = [!activation[key], title]);
};

export {
  getTodoListData,
  updateTodoListData,
  deleteListTask,
  subscribe,
  update,
  getHistoryData,
  postHistoryData,
};
