import * as util from "../../util/Util";

function createStore(data) {
  return {
    getState(type) {
      return data[type];
    },
    async addState(type, newState) {
      data[type].push(newState);
      util.postData(`http://localhost:${util.port}/${type}`, newState);
    },
    async updateState(type, id, newState) {
      data[type].forEach((el, idx) => {
        if (el.id === +id) data[type][idx] = newState;
      });
      util.updateData(`http://localhost:${util.port}/${type}/${id}`, newState);
    },
    async removeState(type, id) {
      // TODO: client의 store에서도 삭제해야함.
      util.deleteData(`http://localhost:${util.port}/${type}/${id}`);
    },
  };
}

export { createStore };
