// server.js
import jsonServer from 'json-server';
const server = jsonServer.create();
const router = jsonServer.router('todos.json');
const middlewares = jsonServer.defaults();

// db.json를 조작하기 위해 lowdb를 사용
import low from 'lowdb';
import FileSync from 'lowdb/adapters/FileSync.d.ts';
const adapter = new FileSync('todos.json');
const db = low(adapter);

// Set default middlewares (logger, static, cors and no-cache)
server.use(middlewares);

// Add custom routes before JSON Server router
server.delete('/todos', (req, res) => {
  // lowdb를 사용해서 db.json에서 completed: true인 todo를 제거

  //db.get('todos').remove({ completed: true }).write();
  console.log(req);
  // todos를 응답
  res.send(db.get('todos').value());
});

// Use default router
server.use(router);

server.listen(3000, () => {
  console.log('JSON Server is running');
});
