import { fetchData, postData } from "../utils/utils.js";
import { serverURL } from "../constants/urlPath.js";

export const getTodoListData = async () => {
  const todoListData = [];
  const ListData = await fetchData(`${serverURL}/todoList`);
  for (const list of ListData) {
    const task = await fetchData(`${serverURL}/${list}`);
    todoListData.push({ [list]: task });
  }
  return todoListData;
};

export const updateTodoListData = (newTask, title) => {
  postData(`${serverURL}/${title}`, newTask);
};

const subscribers = {
  //   registration: []
  //   newTask: []
};

const activation = {
  //   registration: false
  //   newTask: false
};

export const subscribe = (key, notify) => {
  if (activation[key] === undefined) {
    activation[key] = false;
    let value = activation[key];

    Object.defineProperty(activation, key, {
      get: () => {
        return value;
      },
      set: ([newValue, title, newTask]) => {
        value = newValue;
        newTask && updateTodoListData(newTask, title);
        subscribers[key].forEach((notify) => {
          notify(activation[key], title, newTask);
        });
      },
    });
  }

  subscribers[key] = subscribers[key] || [];
  subscribers[key].push(notify);
};

export const update = (key, title, newTask) => {
  if (newTask) return (activation[key] = [activation[key] + 1, title, newTask]);
  activation[key] = [!activation[key], title];
};
