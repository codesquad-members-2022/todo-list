import { TODO_LIST_URL } from '../constants/constant';

export const fetchData = async url => {
  try {
    const data = await fetch(url);
    return data.json();
  } catch (err) {
    console.log(err);
  }
};

export const $ = (target, element = document) => element.querySelector(target);
export const $$ = (target, element = document) => element.querySelectorAll(target);

export const saveToDB = async (url, store) => {
  return await fetch(url, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(store),
  });
};

export const appendElementsToParent = (parent, ...elements) => {
  elements.forEach(element => parent.appendChild(element));
};

export const delay = time => {
  return new Promise(resolve => {
    setTimeout(resolve, time);
  });
};

export const rerender = (store, element = 'main') => {
  const mainView = document.querySelector(`.${element}`);
  mainView.innerHTML = '';
  store.inform(store.getStore('main'));
};

export const reflectStore = store => {
  const columns = [...document.querySelectorAll('.column')];
  const columnDatas = store.getStore('main');
  columns.forEach((column, columnIdx) => {
    const cards = [...column.querySelectorAll('.task__card:not(.new-card)')];
    const columnData = columnDatas[columnIdx];
    const columnName = columnData.className;
    columnData.tasks = cards.map(card => {
      return cardToJSON(card, columnName);
    });
    columnData.total = columnData.tasks.length;
    const url = TODO_LIST_URL + '/' + columnData.id;
    saveToDB(url, columnData);
  });
};

const cardToJSON = (cardElement, columnName) => {
  const header = cardElement.querySelector('.card__contents__header').innerHTML;
  const main = cardElement.querySelector('.card__contents__main').innerHTML;
  const footer = cardElement.querySelector('.card__contents__footer').innerHTML;
  const cardId = cardElement.dataset.id;
  const datetime = cardElement.dataset.datetime;
  const status = columnName;
  return { header, main, footer, cardId, datetime, status };
};
