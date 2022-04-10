import './index.js';
import '../styles/main.scss';
import db from './store';

// db.read('store').push(data).write();

db.data = { mock: [1, 2, 3] };
db.write();
console.log(db.data);
