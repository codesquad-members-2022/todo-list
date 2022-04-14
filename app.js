// import css from './style/index.scss';
import TodoNoticeAnimation from './components/TodoNoticeAnimation.js';
import TodoColumn from './components/TodoColumn.js';
import Todo from './components/Todo.js';
import { getLocalStorageByKey, getTodosByStatus } from './utils/localStorage.js';
import { onBodyMouseDown, onBodyMouseMove, onBodyMouseUp } from './utils/eventDragHandler.js';
import { $ } from './utils/dom.js';
import { handleNotice } from './utils/action.js';

const app = () => {
  const todos = getLocalStorageByKey('todos') ?? [];
  const notices = getLocalStorageByKey('notices') ?? [];
  localStorage.setItem('todos', JSON.stringify(todos));
  localStorage.setItem('notices', JSON.stringify(notices));

  new TodoNoticeAnimation();
  onBodyMouseDown();
  onBodyMouseMove();
  onBodyMouseUp();
  createColumns();
  createNotices();
};

const createColumns = () => {
  const columns = ['해야할일', '하고있는일', '완료한일'];
  const columnsWrapper = $('.column-section');
  columns.forEach(status => {
    const column = new TodoColumn(status);
    const todos = getTodosByStatus(status);
    const count = todos.length;
    column.setCount(count);
    columnsWrapper.insertAdjacentHTML('beforeend', column.render());
    column.handleEventListener();

    todos.forEach(element => {
      const newTodo = new Todo(element, column.handleMinusCount);
      $(`.${element.status}`).insertAdjacentHTML('afterend', newTodo.render());
      newTodo.handleEventListener();
    });
  });
};

const createNotices = () => {
  const notices = getLocalStorageByKey('notices') ?? [];
  notices.forEach(notice => {
    handleNotice(notice);
  });
};

app();
