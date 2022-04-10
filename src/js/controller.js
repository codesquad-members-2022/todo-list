import { icons, TODO_LIST_URL } from './constants/constant.js';
import { appendElementsToBody, fetchData } from './utils/util.js';
import { insertColumns } from './views/columns.js';
import { insertAllCardToColumn } from './views/card.js';
import { onAddBtnClick } from './views/newCard.js';
import { createStore } from './model/store';

export const controller = async views => {
  const { header, main } = views;

  const todoListData = await fetchData(TODO_LIST_URL);
  const store = createStore();
  store.setStore('main', todoListData);
  insertColumns(main, store, 'main', icons);
  insertAllCardToColumn(main, store, 'main', icons);
  appendElementsToBody(header, main);
  onAddBtnClick();
};
