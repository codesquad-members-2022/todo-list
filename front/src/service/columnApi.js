import axios from "axios";

const baseURL = "http://localhost:3000/api/column/";
const client = axios.create({
  baseURL,
});

const columnApi = {
  getColumns: async () => {
    const response = await client.get();
    return response.data.results;
  },
  getColumnById: async (id) => {
    const response = await client.get(id);
    return response.data.results;
  },
};

export default columnApi;
