import { $ } from './utils/utils';
import '../styles/main.scss';
import TodoColumns from './Views/TodoColumns';
import store from './store';

function init() {
  const todoColumns = new TodoColumns($('.todo-columns'));
  todoColumns.init(store.getAllColumns());
}

window.addEventListener('DOMContentLoaded', init);
