import TodoNoticeAnimation from './components/TodoNoticeAnimation.js';
import TodoColumn from './components/TodoColumn.js';
import Todo from './components/Todo.js';
import { getLocalStorageByKey } from './utils/localStorage.js';

const app = () => {
  const todos = getLocalStorageByKey('todos') ? getLocalStorageByKey('todos') : [];
  localStorage.setItem('todos', JSON.stringify(todos));

  new TodoNoticeAnimation();
  createColumns();
  createTodos();
};

const createColumns = () => {
  const columns = ['todo', 'ing', 'complete'];
  columns.forEach(status => {
    const column = new TodoColumn(status);
    const count = colulmnTodoCount(status);
    column.setCount(count);
    column.render();
  });
};

const colulmnTodoCount = status => {
  const todos = getLocalStorageByKey('todos');
  if (!todos) return;
  return todos.filter(todo => todo.status === status).length;
};

const createTodos = () => {
  const todos = getLocalStorageByKey('todos');
  todos.forEach(todo => {
    const newTodo = new Todo(todo);
    document.querySelector(`.${todo.status}`).insertAdjacentHTML('afterend', newTodo.render());
    newTodo.run();
  });
};

app();
