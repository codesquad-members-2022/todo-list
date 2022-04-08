import { $ } from './util';
import { createCardTemplate, createColumnTemplate } from './template';

const getData = async (keyword) => {
  const url = `http://localhost:3001/${keyword}`;
  const res = await fetch(url);
  const json = await res.json();
  return json;
};

export const renderColumns = async () => {
  const columnData = await getData('columns');
  columnData.forEach((title) => {
    $('main').insertAdjacentHTML('beforeend', createColumnTemplate(title));
  });
};

const addCardHandler = (e) => {
  const columnList = e.target.closest('.column').querySelector('.column-list');
  !columnList.querySelector('.active')
    ? columnList.insertAdjacentHTML('afterbegin', createCardTemplate())
    : columnList.firstElementChild.remove();
};

export function addCardEvent() {
  $('main').addEventListener('click', (e) => {
    if (e.target.closest('.plus-btn')) {
      addCardHandler(e);
    }
  });
}
