import { $, addClass, eventDelegate, removeClass } from '../utils/utils';

class Modal {
  constructor() {
    this.$modal = $('.modal');
    this.contents = { title: '', accentText: '' };
    this.addEventHandlers();
  }

  addEventHandlers() {
    eventDelegate({
      target: this.$modal,
      eventName: 'click',
      selector: '.cancel-button',
      handler: () => {
        this.closeModal();
      },
    });
  }

  openModal() {
    const { title, accentText } = this.contents;
    const $modalTitle = $('.modal-title', this.$modal);
    const $modalAccentText = $('.button-accent', this.$modal);
    $modalTitle.textContent = title;
    $modalAccentText.textContent = accentText;
    removeClass('hidden', this.$modal);
  }

  closeModal() {
    addClass('hidden', this.$modal);
  }

  setContents({ title, accentText }) {
    this.contents = { title, accentText };
  }
}

const modal = new Modal();
export default modal;
