import { request, BASE_URL, HTTP_METHOD, requestWithoutJson } from './index.js';

const TaskApi = {
  getAllTasks() {
    return request(`${BASE_URL}/tasks?_expand=user`);
  },

  editTask(taskInfo, taskId) {
    return requestWithoutJson(
      `${BASE_URL}/tasks/${taskId}`,
      HTTP_METHOD.PATCH(taskInfo),
    );
  },

  enrollTask(taskInfo) {
    return requestWithoutJson(`${BASE_URL}/tasks`, HTTP_METHOD.POST(taskInfo));
  },

  deleteTask(taskId) {
    return requestWithoutJson(`${BASE_URL}/tasks/${taskId}`, HTTP_METHOD.DELETE());
  }
};

export default TaskApi;
