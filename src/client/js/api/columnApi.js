import { request, BASE_URL } from './index.js';

const ColumnApi = {
  getAllColumns() {
    return request(`${BASE_URL}/columns`);
  },
};

export default ColumnApi;
