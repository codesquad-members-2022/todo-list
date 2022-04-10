export const createStore = () => {
  const store = {};
  const getStore = elementName => {
    return store[elementName];
  };
  const getStoreAll = () => {
    return store;
  };
  const setStore = (elementName, item) => {
    store[elementName] = item;
  };
  return { getStore, getStoreAll, setStore };
};
