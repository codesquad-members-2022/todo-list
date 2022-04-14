export const createStore = () => {
  const store = {};
  const getStore = elementName => {
    return store[elementName];
  };
  const setStore = (elementName, item) => {
    store[elementName] = item;
  };
  return { getStore, setStore };
};
