import Model from './Model.js';
import View from './view.js';
import Controller from './Controller.js';
import { getData } from '../mok-data/data.js';

export async function initTodo() {
  const workListData = await getData();

  const model = new Model(workListData);
  const view = new View();
  new Controller(model, view);
}
