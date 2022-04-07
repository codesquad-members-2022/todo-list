import TodoHeader from './components/TodoHeader.js';
import TodoColumn from './components/TodoColumn.js';
import { dummyTodos } from './mocks/todos.js';
import Todo from './components/Todo.js';

const app = () => {
  // 하나의 객체
  const todos = JSON.parse(localStorage.getItem('todos')) ? JSON.parse(localStorage.getItem('todos')) : [];

  if (todos !== null) {
    localStorage.setItem('todos', JSON.stringify(todos));
  }

  new TodoColumn('todo');
  new TodoColumn('ing');
  new TodoColumn('complete');

  new TodoHeader();

  dummyDataload();
};

const dummyDataload = () => {
  dummyTodos.forEach(e => {
    const newTodo = new Todo(e);
    if (e.status == 'ing') {
      document.querySelector(`.ing`).insertAdjacentHTML('afterend', newTodo.render());
    } else if (e.status == 'todo') {
      document.querySelector(`.todo`).insertAdjacentHTML('afterend', newTodo.render());
    } else if (e.status == 'complete') {
      document.querySelector(`.complete`).insertAdjacentHTML('afterend', newTodo.render());
    }
  });
};

app();
