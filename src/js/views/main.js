import { fetchData } from '../utils/util.js';

export const createMainObj = async url => {
  const store = await fetchData(url);
  const getStore = () => {
    return store;
  };
  // const setStore = (item) =>{
  //   store.push(item);
  // }
  return (() => {
    const element = document.createElement('main');
    const main = { element, getStore };
    main.element.classList.add('main');
    return main;
  })();
};
