import axios from "axios";

const API_END_POINT = `__API_END_POINT__`; // replace by webpack
const baseURL = `${API_END_POINT}/column/`;
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
