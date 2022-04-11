import { $, closest, containClass } from './util';
import { createCardTemplate, createColumnTemplate } from './template';

const getData = async (keyword) => {
  const url = `http://localhost:3001/${keyword}`;
  const res = await fetch(url);
  const json = await res.json();
  return json;
};

export const renderColumns = async () => {
  const columnData = await getData('columns');
  const columns = columnData.map((title) => createColumnTemplate(title));
  $('main').insertAdjacentHTML('beforeend', columns.join(' '));
};

const addCardHandler = (e) => {
  const columnList = $('.column-list', closest('.column', e.target));
  $('.active', columnList)
    ? columnList.firstElementChild.remove()
    : columnList.insertAdjacentHTML('afterbegin', createCardTemplate());
};

export const cancelAddHandler = (e) => {
  const columnList = $('.column-list', closest('.column', e.target));
  columnList.firstElementChild.remove();
};

export const addCardEvent = () => {
  $('main').addEventListener('click', (e) => {
    if (closest('.plus-btn', e.target)) {
      addCardHandler(e);
    }

    if (containClass(e.target, 'normal-btn')) {
      cancelAddHandler(e);
    }

    if (containClass(e.target, 'item-title')) {
      e.target.addEventListener('input', () => inputFocusHandler(e.target));
    }
  });
};

const inputFocusHandler = (target) => {
  const accentBtn = $('.accent-btn', closest('.list_item', target));

  target.value
    ? accentBtn.removeAttribute('disabled')
    : (accentBtn.disabled = 'true');
};
