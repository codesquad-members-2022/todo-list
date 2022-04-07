import { $ } from './util';

const createColumnTemplate = (title) => {
  return ` 
  <div class="column">
    <div class="column-header">
      <div>
        <h2 class="column-title">${title}</h2>
        <span class="item-count">0</span>
      </div>
      <div>
        <button class="plus-btn"><span class="icon-plus"></span></button>
        <button class="close-btn"><span class="icon-close"></span></button>
      </div>
    </div>
    <ul class="column-list"></ul>
  </div>
  `;
};

const getColumnsData = async () => {
  const url = 'http://localhost:3001/columns';
  const res = await fetch(url);
  const json = await res.json();
  return json;
};

export const renderColumns = async () => {
  const columnData = await getColumnsData();
  columnData.forEach((title) => {
    $('main').insertAdjacentHTML('beforeend', createColumnTemplate(title));
  });
};

const CreateCardTemplate = () => {};
