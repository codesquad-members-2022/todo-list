export default class Modal {
  init() {

  }

  template() {
    return `
      <div class="modal">
        <div div class="modal__container">
          <div class="modal__check-message">
            선택한 카드를 삭제할까요?
          </div>
          <div class="modal__btns">
            <button class="modal__btn--cancel">취소</button>
            <button button class="modal__btn--remove">삭제</button>
          </div>
        </div>
      </div>
    `
  }
}
