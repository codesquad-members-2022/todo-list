import HeaderModel from './Model.js';
import HeaderView from './View.js';

export default class Header {
  constructor() {
    this.model = new HeaderModel();
    this.view = new HeaderView();
  }
}
