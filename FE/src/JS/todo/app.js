import Model from './Model.js';
import View from './view.js';
import Controller from './Controller.js';
import Drag from '../drag-drop/drag-drop.js';
import { fetchRequest } from '../utility/fetchRequest.js';

export async function initTodo() {
  const workListData = await fetchRequest('../src/js/mok-data/mokData.json');
  const model = new Model(workListData);
  const view = new View();
  new Controller(model, view);

  const drag = new Drag();
  drag.addDragEvent();
}
