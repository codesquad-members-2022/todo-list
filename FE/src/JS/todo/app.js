import Model from './Model.js';
import View from './view.js';
import Controller from './Controller.js';
import { getData } from '../mok-data/data.js';
import Drag from '../drag-drop/drag-drop.js';
import { fetchRequest } from '../utility/fetchRequest.js';

export async function initTodo() {
  // const workListData = await getData();
  const workListData = await fetchRequest('/works?userId=ikjo');
  const model = new Model(workListData);
  const view = new View();
  new Controller(model, view);

  const drag = new Drag();
  drag.addDragEvent();
}
