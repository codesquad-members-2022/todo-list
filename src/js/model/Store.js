import * as util from "../../util/Util";

function createStore(data) {
  return {
    getState(type) {
      return data[type];
    },
    async addState(type, newState) {
      data[type].push(newState);
      // fetch API - POST
      console.log(type, newState);
      util.postData(`http://localhost:${util.port}/${type}`, newState);
    },
    async updateState(type, newState) {
      /*
      newState의 id와 data[type]의 id가 일치하는 인덱스를 찾는다.
      해당 인덱스의 값을 바꾼다.
      */
    },
    async removeState() {
      /* state 정보 삭제*/
    },
  };
}

export { createStore };
