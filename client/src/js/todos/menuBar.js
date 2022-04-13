import { $, closest } from '../util.js';

export class MenuBar {
  constructor() {
    this.target = $('.header');
  }

  init() {
    this.target.addEventListener('click', this.showMenuHandler);
  }

  showMenuHandler = (e) => {
    if (closest('.menu-btn', e.target)) {
      $('.menu-bar').classList.add('focus');
    }
    if (closest('.menu-close-btn', e.target)) {
      $('.menu-bar').classList.remove('focus');
    }
  };
}
