import TodoNoticeAnimation from './components/TodoNoticeAnimation.js';
import TodoColumn from './components/TodoColumn.js';
import Todo from './components/Todo.js';
import TodoCount from './components/TodoCount.js';
const columns = ['todo', 'ing', 'complete'];

const app = () => {
  columns.forEach(value => {
    new TodoColumn(value).render();
  });

  new TodoNoticeAnimation();

  const todos = JSON.parse(localStorage.getItem('todos')) ? JSON.parse(localStorage.getItem('todos')) : [];

  if (todos !== null) {
    localStorage.setItem('todos', JSON.stringify(todos));
    dataLoad(todos);
  }
};

const dataLoad = todos => {
  // todos 배열에서 status(ing,todo,complete)를 각각 필터링하여 그에 맞는 배열에 넣는다.

  const todoArr = todos.filter(todo => todo.status === columns[0]);
  const ingArr = todos.filter(todo => todo.status === columns[1]);
  const completeArr = todos.filter(todo => todo.status === columns[2]);

  todoArr.forEach(todo => {
    const newTodo = new Todo(todo);
    document.querySelector(`.${columns[0]}`).insertAdjacentHTML('afterend', newTodo.render());
    newTodo.run();
  });

  ingArr.forEach(todo => {
    const newTodo = new Todo(todo);
    document.querySelector(`.${columns[1]}`).insertAdjacentHTML('afterend', newTodo.render());
    newTodo.run();
  });

  completeArr.forEach(todo => {
    const newTodo = new Todo(todo);
    document.querySelector(`.${columns[2]}`).insertAdjacentHTML('afterend', newTodo.render());
    newTodo.run();
  });

  new TodoCount(columns[0], todoArr.length).render();
  new TodoCount(columns[1], ingArr.length).render();
  new TodoCount(columns[2], completeArr.length).render();
};

app();
