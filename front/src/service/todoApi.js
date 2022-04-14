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
};

export default todoApi;
