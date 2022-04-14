import jsonServer from 'json-server';
import { Low, JSONFile } from 'lowdb';
import lodash from 'lodash';

const server = jsonServer.create();
const router = jsonServer.router('db.json');
const middlewares = jsonServer.defaults();

server.use(middlewares);
server.use(jsonServer.bodyParser);

const db = new Low(new JSONFile('db.json'));
await db.read();

db.chain = lodash.chain(db.data);
server.delete('/todos/:id', (req, res) => {
  const id = Number(req.params.id);

  const todo = db.chain.get('todos').find({ id: id }).value();
  if (todo) {
    todo.isDeleted = true;
    db.write();
    res.send({ status: 'OK' });
    return;
  }

  res.send({ status: 'FAIL' });
});

server.get('/todos', (req, res) => {
  const todos = db.chain.get('todos').filter({ isDeleted: false }).value();
  res.send(todos);
});

server.post('/todos', (req, res) => {
  const todo = req.body;
  const todos = db.chain.get('todos').value();
  todos.push(todo);
  db.write();

  res.send({ status: 'OK' });
});

server.put('/todos', (req, res) => {
  const todo = req.body;

  const existTodo = db.chain.get('todos').find({ id: todo.id }).value();
  if (existTodo) {
    res.send({ status: 'OK' });
    db.write();
    return;
  }

  res.send({ status: 'FAIL' });
});

server.use(router);

server.listen(5000, () => {
  console.log('JSON Server is running');
});
