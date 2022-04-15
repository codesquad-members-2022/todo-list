import axios from "axios";

const API_END_POINT = `__API_END_POINT__`; // replace by webpack
const baseURL = `${API_END_POINT}/todo/`;
const client = axios.create({
  baseURL,
});

const todoApi = {
  getTodos: async () => {
    const response = await client.get();
    return response.data.results;
  },
  deleteTodo: async (id) => {
    const response = await client.delete(`delete/${id}`);
    return response.data.results;
  },
  updateTodo: async (todoId, updateData) => {
    const response = await client.patch(`update/${todoId}`, updateData);
    return response.data.results;
  },
  createTodo: async (newData) => {
    const response = await client.post(`create`, newData);
    return response.data.results;
  },
};

export default todoApi;
