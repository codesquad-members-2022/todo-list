export const Store = {
  state: {},

  getAllDataByKey(key) {
    return observer.notifyAll(key);
  },
};

export const observer = {
  subscribers: {},
  subscribe(key, subscriber) {
    if (!this.subscribers[key]) {
      this.subscribers[key] = [];
    }
    this.subscribers[key].push(subscriber);
  },

  notify(key, state) {
    this.subscribers[key].forEach(subscriber => subscriber(state));
  },

  notifyAll(key) {
    return this.subscribers[key].reduce((acc, cur) => {
      /**
       * TODO:// cur의 값들 acc에 누적하기
       * */
    }, {});
  },
};
