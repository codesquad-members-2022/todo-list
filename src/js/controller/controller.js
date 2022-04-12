import { TODO_LIST_URL } from '../constants/constant.js';
import { appendElementsToParent, fetchData } from '../utils/util.js';
import { insertColumns } from '../views/columnsView.js';
import { insertAllCardToColumn } from '../views/cardView.js';
import { onAddBtnClick } from '../views/newCardView.js';
import { createStore } from '../store/store';
import { onClickCardDeleteBtn, onMouseOutCardDeleteBtn, onMouseOverCardDeleteBtn } from '../views/cardDeleteView.js';

export const controller = async views => {
  const { headerView, mainView } = views;
  const app = document.getElementById('app');
  const todoListData = await fetchData(TODO_LIST_URL);
  const store = createStore();
  store.setStore('main', todoListData);
  appendElementsToParent(app, headerView, mainView);
  insertColumns(store.getStore('main'), mainView);
  insertAllCardToColumn(store.getStore('main'), mainView);
  onAddBtnClick();
  onMouseOverCardDeleteBtn();
  onMouseOutCardDeleteBtn();
  onClickCardDeleteBtn(store);
  setObserver(store);
};

const setObserver = store => {
  store.setObserver(insertColumns);
  store.setObserver(insertAllCardToColumn);
};
