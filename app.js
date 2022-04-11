import css from './style/index.scss';
import TodoNoticeAnimation from './components/TodoNoticeAnimation.js';
import TodoColumn from './components/TodoColumn.js';
import Todo from './components/Todo.js';
import { getLocalStorageByKey } from './utils/localStorage.js';
import { onBodyMouseMove, onBodyMouseUp } from './utils/eventDragHandler.js';
import { $ } from './utils/dom.js';

const app = () => {
  const todos = getLocalStorageByKey('todos') ? getLocalStorageByKey('todos') : [];
  localStorage.setItem('todos', JSON.stringify(todos));

  new TodoNoticeAnimation();
  onBodyMouseMove();
  onBodyMouseUp();
  createColumns();
  createTodos();
};

const createColumns = () => {
  const columns = ['todo', 'ing', 'complete'];
  columns.forEach(status => {
    const column = new TodoColumn(status);
    const count = columnTodoCount(status);
    column.setCount(count);
    column.render();
  });
};

const columnTodoCount = status => {
  const todos = getLocalStorageByKey('todos');
  if (!todos) return;
  return todos.filter(todo => todo.status === status).length;
};

const createTodos = () => {
  const todos = getLocalStorageByKey('todos');
  todos.forEach(todo => {
    const newTodo = new Todo(todo);
    $(`.${todo.status}`).insertAdjacentHTML('afterend', newTodo.render());
    newTodo.setElement();
    newTodo.handleEventListener();
  });
};

app();
