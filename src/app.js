import { $ } from './utils/utils.js';
import './index.js';
import '../styles/main.scss';
import db from './webdb.js';
import { TodoColumns } from './Views/TodoColumns.js';

const todoColumns = new TodoColumns($('.todo-columns'));

// 처음에 웹 사이트 접속시 db에있는 데이터를 렌더링 === Read
todoColumns.init(db.getColumns());
