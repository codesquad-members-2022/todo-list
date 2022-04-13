import axios from "axios";

const baseURL = "http://localhost:3000/api/log/";
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
