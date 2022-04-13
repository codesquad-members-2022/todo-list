import Column from '../components/Column.js';
import Header from '../components/Header.js';
import ActionLayer from '../components/ActionLayer.js';
import { ColumnStore } from '../store/index.js';
import Card from '../components/Card.js';
import Alert from '../components/Alert.js';
import InputCard from '../components/InputCard.js';
import Action from '../components/Action.js';

export const renderActions = ({ container, actions }) => {
  const fragment = document.createDocumentFragment();
  if (!actions.length) {
    const listItem = document.createElement('li');
    listItem.innerText = '활동 기록이 없습니다.'
    fragment.append(listItem);
  } else {
    actions.reverse().forEach(action => {
      const listItem = document.createElement('li');
      listItem.classList.add('action-list-item');
      listItem.dataset.id = action.id;
      new Action(listItem, { action });
      fragment.appendChild(listItem);
    });
  }
  container.innerHTML = '';
  container.append(fragment);
};

export const renderAlert = ({ cardId }) => {
  new Alert(document.querySelector('.alert-wrapper'), { cardId });
};

export const renderEditedInputCard = ({ target, column }) => {
  const listItem = document.createElement('li');
  const inputCard = new InputCard(listItem, {
    column,
    mode: 'edit',
    card: target,
    title: target.querySelector('.card-title').innerText,
    contents: target.querySelector('.card-contents').innerText,
  });
  target.insertAdjacentElement('afterend', listItem);
  return inputCard;
};

export const renderNewInputCard = ({ container, column }) => {
  const listItem = document.createElement('li');
  const inputCard = new InputCard(listItem, { column, mode: 'new' });
  container.insertAdjacentElement('afterbegin', listItem);
  return inputCard;
};

const renderColumns = ({ container, columns }) => {
  const fragment = document.createDocumentFragment();
  columns.forEach(column => {
    const section = document.createElement('section');
    section.classList.add('column');
    section.dataset.id = column.id;
    new Column(section, { column });
    fragment.appendChild(section);
  });
  container.append(fragment);
};

export const renderCards = ({ container, tasks, column }) => {
  const fragment = document.createDocumentFragment();
  tasks.forEach(task => {
    const listItem = document.createElement('li');
    listItem.dataset.id = task.id;
    task.column = column;
    new Card(listItem, task);
    fragment.appendChild(listItem);
  });
  container.append(fragment);
};

export const render = () => {
  new Header(document.querySelector('header'));
  new ActionLayer(document.querySelector('.action-layer'));
  renderColumns({
    container: document.querySelector('.wrapper'),
    columns: ColumnStore.getAllColumns(),
  });
};
