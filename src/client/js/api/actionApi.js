import { request, BASE_URL } from './index.js';

const ActionApi = {
  getAllActions() {
    return request(`${BASE_URL}/actions?_expand=user`);
  }
}

export default ActionApi;
