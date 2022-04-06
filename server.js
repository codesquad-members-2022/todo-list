import jsonServer from 'json-server';
import {Low, JSONFile} from 'lowdb';

const server = jsonServer.create();
const router = jsonServer.router('db.json');
const middlewares = jsonServer.defaults();

// db.json를 조작하기 위해 lowdb를 사용
const adapter = new JSONFile('db.json');
const db = new Low(adapter);

await db.read();

// Set default middlewares (logger, static, cors and no-cache)
server.use(middlewares);

server.use(jsonServer.bodyParser);
server.use(async (req, res, next) => {
  const actionContents = {
    POST: [req.body.column, req.body.title],
    DELETE: [req.body.column, req.body.title],
    PUT: [req.body.title, req.body.contents],
  };

  next();
  if (req.method === 'GET') return;

  db.data.action.push({
    user: 1,
    type: req.method,
    contents: actionContents[req.method],
    executedTime: new Date(),
  });
  await db.write();
});

// Use default router
server.use(router);

server.listen(3000, () => {
  console.log(`JSON Server is running on http://localhost:3000`);
});
