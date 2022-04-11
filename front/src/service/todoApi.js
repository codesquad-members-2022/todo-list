import axios from "axios";

const baseURL = "http://localhost:3000/api/todo/";
const client = axios.create({
  baseURL,
});

const todoApi = {
  getTodos: async () => {
    const response = await client.get();

    return response.data.results;
  },
};

export default todoApi;
