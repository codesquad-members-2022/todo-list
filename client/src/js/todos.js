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

export const cancelAddHandler = (e) => {
  const columnList = e.target.closest('.column').querySelector('.column-list');
  columnList.firstElementChild.remove();
};

export const addCardEvent = () => {
  $('main').addEventListener('click', (e) => {
    if (e.target.closest('.plus-btn')) {
      addCardHandler(e);
    }

    if (e.target.classList.contains('normal-btn')) {
      cancelAddHandler(e);
    }

    if (e.target.classList.contains('item-title')) {
      e.target.addEventListener('input', () => inputFocusHandler(e.target));
    }
  });
};

const inputFocusHandler = (target) => {
  const accentBtn = target.closest('.list_item').querySelector('.accent-btn');

  !target.value
    ? (accentBtn.disabled = 'true')
    : accentBtn.removeAttribute('disabled');
};
