// import css from './style/index.scss';
import TodoNoticeAnimation from './utils/TodoNoticeAnimation.js';
import TodoColumn from './components/TodoColumn.js';
import Todo from './components/Todo.js';
import { getLocalStorageByKey, getTodosByStatus } from './utils/localStorage.js';
import { onBodyMouseDown, onBodyMouseMove, onBodyMouseUp, onContextMenu } from './utils/eventDragHandler.js';
import { $ } from './utils/dom.js';
import { handleNotice } from './utils/action.js';
import Fab from './components/Fab.js';

const app = () => {
  const todos = getLocalStorageByKey('todos') ?? [];
  const notices = getLocalStorageByKey('notices') ?? [];
  localStorage.setItem('todos', JSON.stringify(todos));
  localStorage.setItem('notices', JSON.stringify(notices));

  new TodoNoticeAnimation();
  onBodyMouseDown();
  onBodyMouseMove();
  onBodyMouseUp();
  onContextMenu();
  createColumns();
  createNotices();

  const fab = new Fab();
  fab.handleEventListener();
};

const createColumns = () => {
  const columns = ['해야할일', '하고있는일', '완료한일'];
  localStorage.setItem('column', JSON.stringify(columns));

  const columnsWrapper = $('.column-section');
  columns.forEach(status => {
    const column = new TodoColumn(status);
    const todos = getTodosByStatus(status);
    const count = todos.length;
    column.setCount(count);
    columnsWrapper.insertAdjacentHTML('beforeend', column.render());
    column.handleEventListener();
    createTodos(column, todos);
  });
};

const createTodos = (column, todos) => {
  todos.forEach(element => {
    const newTodo = new Todo(element, column.handleMinusCount);
    $(`.${element.status}`).insertAdjacentHTML('afterend', newTodo.render());
    newTodo.handleEventListener();
  });
};

const createNotices = () => {
  const notices = getLocalStorageByKey('notices') ?? [];
  notices.forEach(notice => {
    handleNotice(notice);
  });
};

app();
