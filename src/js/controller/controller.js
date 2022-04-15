import { TODO_LIST_URL } from '../constants/constant.js';
import { appendElementsToParent, fetchData, saveToDB } from '../utils/util.js';
import { insertColumns, onDeleteAllCardInnerColumn } from '../views/columnsView.js';
import { addNewCardToColumn, insertAllCardToColumn } from '../views/cardView.js';
import { insertEditCard, onAddBtnClick } from '../views/newCardView.js';
import { createStore } from '../store/store';
import { attatchDeleteEventsToView } from '../views/cardDeleteView.js';
import { onDragEvent } from '../views/dragView.js';

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
  onDeleteAllCardInnerColumn(store);
  store.setObserverPipe(insertColumns, insertAllCardToColumn);
  attatchDeleteEventsToView(mainView, store);
  onAddBtnClick(store);
  // onCardDoubleClick(store);
  onDragEvent(store);
};
