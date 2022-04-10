import { headerTemplate } from '../Utils/template.js';

export default class HeaderView {
  constructor() {
    this.render();
  }

  render() {
    const body = document.querySelector('body');
    const headerHtml = headerTemplate();
    body.insertAdjacentHTML('afterbegin', headerHtml);
  }
}
