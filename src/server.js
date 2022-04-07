const jsonServer = require('json-server');
const low = require('lowdb');

const server = jsonServer.create();
const router = jsonServer.router('db.json');
const middlewares = jsonServer.defaults();

const FileSync = require('lowdb/adapters/FileSync');

const adapter = new FileSync('db.json');
const db = low(adapter);

// Set default middlewares (logger, static, cors and no-cache)
server.use(middlewares);
server.use(jsonServer.bodyParser);

// db logic

server.get('/mock', (req, res) => {
  res.send(db.get('mock').value());
});

server.use(router);
server.listen(3000, () => {
  console.log('JSON Server is running');
});
