// server.js
import jsonServer from 'json-server';
const server = jsonServer.create();
const router = jsonServer.router('todos.json');
const middlewares = jsonServer.defaults();

import lowdb from 'lowdb';
import FileSync from 'lowdb/adapters/FileSync.js';

const db = lowdb(new FileSync('todos.json'));
server.use(middlewares);
// server.use(
//   jsonServer.rewriter({
//     '/todos/*': '/$1',
//   })
// );

server.delete('/todos/completed/:id', (req, res) => {
  const id = Number(req.params.id);

  /**
   * TODO // lowdb 버전 체크 필요
   */
  // todo.isDeleted = true;
  db.get('todos').find({ id: id }).assign({ isDeleted: true }).write();

  res.send(db.get('todos'));
});

server.get('/todos/completed', (req, res) => {
  console.log(req);
});

server.use(router);

server.listen(5000, () => {
  console.log('JSON Server is running');
});
