// import css from './style/index.scss';
import TodoNoticeAnimation from './components/TodoNoticeAnimation.js';
import TodoColumn from './components/TodoColumn.js';
import Todo from './components/Todo.js';
import { getLocalStorageByKey, getTodosByStatus } from './utils/localStorage.js';
import { onBodyMouseMove, onBodyMouseUp } from './utils/eventDragHandler.js';
import { $ } from './utils/dom.js';

const app = () => {
  const todos = getLocalStorageByKey('todos') ? getLocalStorageByKey('todos') : [];
  localStorage.setItem('todos', JSON.stringify(todos));

  new TodoNoticeAnimation();
  onBodyMouseMove();
  onBodyMouseUp();
  createColumns();
};

const createColumns = () => {
  const columns = ['todo', 'ing', 'complete'];
  const columnsWrapper = $('.column-section');
  columns.forEach(status => {
    // 컬럼 생성시 컬럼에 맞는 Todo들 셋팅해줘야할듯.
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

app();
