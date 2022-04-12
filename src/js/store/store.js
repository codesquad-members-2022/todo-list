export const createStore = () => {
  const store = {};
  const subscribers = {};

  const getStore = elementName => {
    return store[elementName];
  };

  const setStore = (elementName, item) => {
    store[elementName] = item;
  };

  const subscribe = (key, fn) => {
    subscribers[key] = fn;
  };

  const notify = (key, ...params) => {
    subscribers[key](...params);
  };

  return { getStore, setStore, subscribe, notify };
};
