import { TODO_LIST_URL } from '../constants/constant.js';
import { appendElementsToParent, fetchData } from '../utils/util.js';
import { insertColumns } from '../views/columnsView.js';
import { addNewCardToColumn, insertAllCardToColumn, onCardDoubleClick } from '../views/cardView.js';
import { onAddBtnClick, insertEditCard } from '../views/newCardView.js';
import { createStore } from '../store/store';
import { onClickCardDeleteBtn, onMouseOutCardDeleteBtn, onMouseOverCardDeleteBtn } from '../views/cardDeleteView.js';

export const controller = async views => {
  const { headerView, mainView } = views;
  const app = document.getElementById('app');
  const todoListData = await fetchData(TODO_LIST_URL);
  const store = createStore();
  store.setStore('main', todoListData);
  store.subscribe('newTodo', addNewCardToColumn);
  store.subscribe('edit', insertEditCard);
  appendElementsToParent(app, headerView, mainView);
  insertColumns(store.getStore('main'), mainView);
  insertAllCardToColumn(store.getStore('main'), mainView);
  setObserver(store);
  addEventListeners(store);
};

const setObserver = store => {
  store.setObserver(insertColumns);
  store.setObserver(insertAllCardToColumn);
};

const addEventListeners = store => {
  onAddBtnClick(store);
  onMouseOverCardDeleteBtn();
  onMouseOutCardDeleteBtn();
  onClickCardDeleteBtn(store);
  onCardDoubleClick(store);
};
