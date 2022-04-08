export const createMainElement = data => {
  const store = data;
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
    // createColumns(mainElement, store, icons);
    // createCards(mainElement, store, icons);
    return main;
  })();
};
