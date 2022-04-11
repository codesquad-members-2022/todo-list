import axios from "axios";

const baseURL = "http://localhost:3000/todo/";
const client = axios.create({
  baseURL,
});

const todoApi = {
  getTodos: async ({ columnId }) => {
    const response = await client.get("", {
      params: {
        columnId,
      },
    });
    return response.data;
  },
};

export default todoApi;
