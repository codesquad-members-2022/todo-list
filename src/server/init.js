import jsonServer from 'json-server';
import { Low, JSONFile } from 'lowdb';
import { addAction, updateOrder } from './middleware.js';

const server = jsonServer.create();
const middlewares = jsonServer.defaults();
const router = jsonServer.router('db.json');
const adapter = new JSONFile('db.json');
const db = new Low(adapter);

db.read();

server.use(middlewares);
server.use(jsonServer.bodyParser);
server.use(updateOrder(router));
server.use(addAction(router));
server.use(router);

export default server;
