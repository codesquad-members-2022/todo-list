import UserApi from '../api/userApi.js';
import Store from './store.js';

class UserStore extends Store {
  #key = 'users';

  async init() {
    const users = await UserApi.getAllUsers();
    this.setState(this.#key, users);
  }

  getUser() {
    return this.getState(this.#key)[0];
  }
}

const userStore = new UserStore();

export default userStore;
