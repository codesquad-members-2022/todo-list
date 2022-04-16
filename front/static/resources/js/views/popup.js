class Popup {
  constructor() {
    this.cardId;
  }

  hide() {
    document.querySelector('.popup-wrap').classList.remove('show');
  }

  show(cardId) {
    this.cardId = cardId;
    document.querySelector('.popup-wrap').classList.add('show');
  }

  #template() {
    return `<div class="popup-wrap">
    <div class="popup card-delete-pop">
    <p class="popup__description">선택한 카드를 삭제할까요?</p>
    <div class="button-wrap">
    <button type="button" class="button--cancle popup__button--cancle">취소</button>
    <button type="button" class="button--submit popup__button--confirm">확인</button>
    </div>
    </div>
    </div>`;
  }

  render() {
    document.querySelector('main').insertAdjacentHTML('beforeend', this.#template());
  }

  #sendDeleteCard(observe) {
    observe('DELETE', null, this.cardId);
    this.hide();
  }

  #addClickEvent(observe) {
    document.querySelector('.popup__button--cancle').addEventListener('click', this.hide);
    document
      .querySelector('.popup__button--confirm')
      .addEventListener('click', this.#sendDeleteCard.bind(this, observe));
  }

  addEvent([observe]) {
    this.#addClickEvent(observe);
  }
}

export { Popup };
