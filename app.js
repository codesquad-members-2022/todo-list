import TodoHeader from './components/TodoHeader.js';
import TodoColumn from './components/TodoColumn.js';

const app = () => {
  // 하나의 객체
  // 각자 다른 메모리주소를 가지고 있는 데이터가 이벤트리스너를 달고있다.
  const 해야할일TodoColumn = new TodoColumn('todo');
  const 하고있는일TodoColumn = new TodoColumn('ing');
  const 완료할일TodoColumn = new TodoColumn('complete');
};

app();
