import ActionStore from './actionStore.js';
import TaskStore from './taskStore.js';
import ColumnStore from './columnStore.js';
import UserStore from './userStore.js';

const initStore = async () => {
  await ActionStore.init();
  await TaskStore.init();
  await ColumnStore.init();
  await UserStore.init();
}

export { ActionStore, TaskStore, ColumnStore, UserStore, initStore };
