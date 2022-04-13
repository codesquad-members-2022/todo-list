export const createStore = () => {
  const store = {};
  const subscriber = [];
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
  const notify = store => {
    subscriber.forEach(callBack => callBack(store));
  };
  return { getStore, setStore, setObserver, notify };
};
