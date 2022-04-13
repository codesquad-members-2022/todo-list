class Popup {
  hidden() {
    document.querySelector('.popup-wrap').style.display = 'none';
  }

  show() {
    document.querySelector('.popup-wrap').style.display = 'block';
  }

  render() {
    document.querySelector('main').insertAdjacentHTML('beforeend', this.template());
  }

  template() {
    return `<div class="popup-wrap">
          <div class="popup card-delete-pop">
            <p class="popup__description">선택한 카드를 삭제할까요?</p>
            <div class="button-wrap">
              <button type="button" class="button--cancle popup-button--cancle">취소</button>
              <button type="button" class="button--submit popup-button--confirm">확인</button>
            </div>
          </div>
        </div>`;
  }
}

export { Popup };
