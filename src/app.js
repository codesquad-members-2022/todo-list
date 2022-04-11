import { $ } from './utils/utils';
import './index';
import '../styles/main.scss';
import db from './webdb';
import TodoColumns from './Views/TodoColumns';

const todoColumns = new TodoColumns($('.todo-columns'));

// 처음에 웹 사이트 접속시 db에있는 데이터를 렌더링 === Read
todoColumns.init(db.getColumns());
