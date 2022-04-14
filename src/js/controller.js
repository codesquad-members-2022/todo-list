import { TODO_LIST_URL } from './constants/constant.js';
import { appendElementsToParent, fetchData } from './utils/util.js';
import { insertColumns } from './views/columnsView.js';
import { insertAllCardToColumn } from './views/cardView.js';
import { onAddBtnClick } from './views/newCardView.js';
import { createStore } from './store/store';

export const controller = async views => {
  const { headerView, mainView } = views;
  const todoListData = await fetchData(TODO_LIST_URL);
  const store = createStore();
  store.setStore('main', todoListData);
  insertColumns(mainView, store.getStore('main'));
  insertAllCardToColumn(mainView, store.getStore('main'));
  appendElementsToParent(document.body, headerView, mainView);
  onAddBtnClick();
};
