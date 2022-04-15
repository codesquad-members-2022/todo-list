import { $, hasClass, addClass, removeClass, eventDelegate } from '../utils/utils';

class Modal {
  constructor() {
    this.$modal = $('.modal');
    this.$target = null;
    this.contents = { title: '', accentText: '' };
    this.addEventHandlers();
  }

  addEventHandlers() {
    const $modalButtons = $('.modal-buttons', this.$modal);
    eventDelegate({
      target: $modalButtons,
      eventName: 'click',
      selector: 'button',
      handler: event => {
        const $button = event.target;
        removeClass('delete-item', this.$target);
        if (hasClass('delete-button', $button)) console.log('삭제로직 수행');

        this.closeModal();
      },
    });
  }

  openModal($target) {
    this.setTargetElement($target);
    const { title, accentText } = this.contents;
    const $modalTitle = $('.modal-title', this.$modal);
    const $modalAccentText = $('.button-accent', this.$modal);
    $modalTitle.textContent = title;
    $modalAccentText.textContent = accentText;
    removeClass('hidden', this.$modal);
  }

  closeModal() {
    addClass('hidden', this.$modal);
    this.setTargetElement(null);
  }

  setContents({ title, accentText }) {
    this.contents = { title, accentText };
  }

  setTargetElement($target) {
    this.$target = $target;
  }
}

const modal = new Modal();
export default modal;
