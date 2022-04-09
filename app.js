import TodoNoticeAnimation from './components/TodoNoticeAnimation.js';
import TodoColumn from './components/TodoColumn.js';
import Todo from './components/Todo.js';
const columns = ['todo', 'ing', 'complete'];

const app = () => {
  const todoColumn = new TodoColumn(columns[0]);
  const ingColumn = new TodoColumn(columns[1]);
  const completeColumn = new TodoColumn(columns[2]);

  new TodoNoticeAnimation();

  const todos = JSON.parse(localStorage.getItem('todos')) ? JSON.parse(localStorage.getItem('todos')) : [];

  if (todos !== null) {
    localStorage.setItem('todos', JSON.stringify(todos));
    dataLoad(todos);
  }
};

const dataLoad = todos => {
  // todos 배열에서 status(ing,todo,complete)를 각각 필터링하여 그에 맞는 배열에 넣는다.
  todos.forEach(todo => {
    const newTodo = new Todo(todo);
    document.querySelector(`.${todo.status}`).insertAdjacentHTML('afterend', newTodo.render());
    newTodo.run(); //** */
  });
};

app();
