import jsonServer from 'json-server';
import { Low, JSONFile } from 'lowdb';
import addAction from './middleware.js';

const server = jsonServer.create();
const middlewares = jsonServer.defaults();
const router = jsonServer.router('db.json');
const adapter = new JSONFile('db.json');
const db = new Low(adapter);

await db.read();

server.use(middlewares);
server.use(jsonServer.bodyParser);
server.use(addAction(router));
server.use(router);

export default server;
