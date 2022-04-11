import { LowSync, LocalStorage } from 'lowdb';
import { v4 as uuidv4 } from 'uuid';

const getData = key => {
  const db = new LowSync(new LocalStorage(key));
  db.read();
  return db.data;
};

const getColumns = () => {
  const columnsData = columns.data.reduce((result, columnKey) => {
    result.push(getData(columnKey));
    return result;
  }, []);

  return columnsData;
};

const initDb = columns => {
  if (columns.data) return;

  const column1Key = uuidv4();
  const column2Key = uuidv4();
  const column3Key = uuidv4();

  const column1 = new LowSync(new LocalStorage(column1Key));
  const column2 = new LowSync(new LocalStorage(column2Key));
  const column3 = new LowSync(new LocalStorage(column3Key));

  columns.data = [column1Key, column2Key, column3Key];

  column1.data = {
    id: column1Key,
    title: '해야할 일',
    cards: [
      {
        id: uuidv4(),
        columnId: column1Key,
        title: 'GitHub 공부하기',
        desc: 'add, commit, push',
        author: 'web',
        createAt: Date.now(),
      },
      {
        id: uuidv4(),
        columnId: column1Key,
        title: '블로그에 포스팅할 것',
        desc: '*Github 공부내용\n*모던 자바스크립트 1장 공부내용',
        author: 'web',
        createAt: Date.now(),
      },
    ],
  };

  column2.data = {
    id: column2Key,
    title: '하고있는 일',
    cards: [
      {
        id: uuidv4(),
        columnId: column1Key,
        title: 'HTML/CSS 공부하기',
        desc: 'input 태그 실습',
        author: 'web',
        createAt: Date.now(),
      },
    ],
  };

  column3.data = {
    id: column3Key,
    title: '완료한 일',
    cards: [],
  };

  columns.write();
  column1.write();
  column2.write();
  column3.write();
};

export default {
  columns,
  getData,
  getColumns,
};

const columns = new LowSync(new LocalStorage('columns'));
columns.read();
initDb(columns);
