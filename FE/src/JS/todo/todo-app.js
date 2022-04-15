import Model from './todo-model.js';
import View from './todo-view.js';
import Controller from './todo-controller.js';
import Drag from '../drag-drop/drag-drop.js';
import { fetchRequest } from '../utility/fetchRequest.js';

export async function initTodo() {
  const workListData = await fetchRequest('/works?userId=ikjo');

  const model = new Model(workListData);
  const view = new View();
  const drag = new Drag();

  new Controller(model, view, drag);
}
