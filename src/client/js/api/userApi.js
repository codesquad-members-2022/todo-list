import { request, BASE_URL } from './index.js';

const UserApi = {
  getAllUsers() {
    return request(`${BASE_URL}/users`);
  },
};

export default UserApi;
