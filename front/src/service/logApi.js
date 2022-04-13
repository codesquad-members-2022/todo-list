import axios from "axios";

const API_END_POINT = `__API_END_POINT__`; // replace by webpack
const baseURL = `${API_END_POINT}/log/`;
const client = axios.create({
  baseURL,
});

const logApi = {
  getTodoLogs: async () => {
    const response = await client.get("todo");
    return response.data.results;
  },
};

export default logApi;
