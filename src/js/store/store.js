export const createStore = () => {
  const store = {};
  const subscriber = [];
  const subscribers = {};

  const getStore = elementName => {
    return store[elementName];
  };

  const setStore = (elementName, item) => {
    store[elementName] = item;
  };

  // const getObserver = elementName => {
  //   return subscriber.filter(observer => Object.keys(observer)[0] === elementName);
  // };

  const setObserver = callBack => {
    subscriber.push(callBack);
  };

  const inform = store => {
    subscriber.forEach(callBack => callBack(store));
  };

  const subscribe = (key, fn) => {
    subscribers[key] = fn;
  };

  const notify = (key, ...params) => {
    subscribers[key](...params);
  };

  return { getStore, setStore, subscribe, notify, inform, setObserver };
};
