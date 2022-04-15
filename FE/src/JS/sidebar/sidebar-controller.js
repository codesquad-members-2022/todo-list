import { fetchRequest } from '../utility/fetchRequest.js';

export default class Controller {
  constructor(model, view) {
    this.model = model;
    this.view = view;
    this.init();
  }

  init() {
    this.view.getLogData = this.getLogData.bind(this);
    this.view.addClickEvent();
  }

  async getLogData() {
    const sidebarData = await fetchRequest('/user-logs?userId=ikjo');

    const actionLogData = this.model.returnLogData(sidebarData);
    let userId = '';

    for (const logData of actionLogData) {
      if (typeof logData === 'string') userId = logData;

      if (Array.isArray(logData)) {
        this.view.startSidebarTimer(userId, logData);
      }
    }
  }
}
