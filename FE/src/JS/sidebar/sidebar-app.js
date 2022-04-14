import Model from './sidebar-model.js';
import View from './sidebar-view.js';
import Controller from './sidebar-controller.js';

export async function initSidebar() {
  const model = new Model();
  const view = new View();
  new Controller(model, view);
}
