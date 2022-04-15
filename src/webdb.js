/* eslint-disable no-param-reassign */
import { LowSync, LocalStorage } from 'lowdb';
import { v4 as uuidv4 } from 'uuid';

const getDb = key => {
  const db = new LowSync(new LocalStorage(key));
  db.read();
  return db;
};

const getData = key => {
  const db = getDb(key);
  return db.data;
};

const setData = (key, value) => {
  try {
    const db = getDb(key);
    db.data = value;
    db.write();
    return true;
  } catch (err) {
    return false;
  }
};

const columns = getDb('columns');

const getColumns = () => {
  const columnsData = columns.data.reduce((result, columnKey) => {
    result.push(getData(columnKey));
    return result;
  }, []);

  return columnsData;
};

const initDb = columnKeys => {
  if (columnKeys.data) return;

  const column1Key = uuidv4();
  const column2Key = uuidv4();
  const column3Key = uuidv4();

  const column1 = getDb(column1Key);
  const column2 = getDb(column2Key);
  const column3 = getDb(column3Key);

  columnKeys.data = [column1Key, column2Key, column3Key];

  column1.data = {
    id: column1Key,
    idx: 0,
    title: '해야할 일',
    cards: [
      {
        id: uuidv4(),
        columnId: column1Key,
        columnIdx: 0,
        title: 'GitHub 공부하기',
        desc: 'add, commit, push',
        author: 'web',
        createAt: Date.now(),
      },
      {
        id: uuidv4(),
        columnId: column1Key,
        columnIdx: 0,
        title: '블로그에 포스팅할 것',
        desc: '*Github 공부내용\n*모던 자바스크립트 1장 공부내용',
        author: 'web',
        createAt: Date.now(),
      },
    ],
  };

  column2.data = {
    id: column2Key,
    idx: 1,
    title: '하고있는 일',
    cards: [
      {
        id: uuidv4(),
        columnId: column2Key,
        columnIdx: 1,
        title: 'HTML/CSS 공부하기',
        desc: 'input 태그 실습',
        author: 'web',
        createAt: Date.now(),
      },
    ],
  };

  column3.data = {
    id: column3Key,
    idx: 2,
    title: '완료한 일',
    cards: [],
  };

  columnKeys.write();
  column1.write();
  column2.write();
  column3.write();
};

export default {
  columns,
  getData,
  setData,
  getColumns,
};

columns.read();
initDb(columns);
