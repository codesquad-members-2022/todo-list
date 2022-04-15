import TaskApi from '../api/taskApi.js';
import Store from './store.js';
import { ActionStore } from './index.js';

class TaskStore extends Store {
  #key = 'tasks';

  async init() {
    await this.setTasks();
  }

  async setTasks() {
    const tasks = await TaskApi.getAllTasks();
    this.setState(this.#key, tasks.reverse());
  }

  async editTask(taskInfo, taskId) {
    if (taskInfo.order && !this.isMoving(taskInfo, taskId)) return;
    const editedTask = await TaskApi.editTask(taskInfo, taskId);
    if (!editedTask) return false;
    await this.setTasks();
    await ActionStore.setActions();
    return true;
  }

  async enrollTask(taskInfo) {
    const order = this.getTasksFilteredWithColumn(taskInfo.columnId).length + 1;
    const newTask = await TaskApi.enrollTask({ ...taskInfo, order });
    if (!newTask) return false;
    await this.setTasks();
    await ActionStore.setActions();
    return true;
  }

  getAllTasks() {
    return this.getState(this.#key);
  }

  getTasksFilteredWithColumn(columnId) {
    const tasks = this.getState(this.#key);
    return tasks.filter(task => task.columnId === columnId).sort((taskA, taskB) => taskB.order - taskA.order);
  }

  getTask(taskId) {
    return this.getState(this.#key).find(task => task.id === parseInt(taskId));
  }

  async deleteTask(taskId) {
    await TaskApi.deleteTask(taskId);
    await this.setTasks();
    await ActionStore.setActions();
  }

  isMoving(taskInfo, taskId) {
    const originTask = this.getTask(taskId);
    return !(taskInfo.columnId === originTask.columnId && taskInfo.order === originTask.order);
  }
}

const taskStore = new TaskStore();

export default taskStore;
