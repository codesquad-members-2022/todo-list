import Model from './todo-model.js';
import View from './todo-view.js';
import Controller from './todo-controller.js';
import Drag from '../drag-drop/drag-drop.js';
import { fetchRequest } from '../utility/fetchRequest.js';

export async function initTodo() {
<<<<<<< HEAD
  const workListData = await fetchRequest(
    '/todo-list/FE/src/js/mok-data/mokData.json'
  );
=======
  const workListData = await fetchRequest('../src/js/mok-data/mokData.json');
>>>>>>> ce69d4f ([remove] 불필요한 데이터 삭제)
  const model = new Model(workListData);
  const view = new View();
  const drag = new Drag();
  
  new Controller(model, view, drag);

}
