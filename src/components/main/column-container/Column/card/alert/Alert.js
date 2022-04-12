import "./alert.scss";
import { Store } from "../../../../../../stores/ColumnStore.js";

export function activateAlert(cardID) {
  renderAlert();
  setEvents(cardID);
}

function renderAlert() {
  document.body.insertAdjacentHTML("afterbegin", getAlertTemplate());
}

function getAlertTemplate() {
  return `
    <div class="alert-container">
      <div class="alert">
        <span class="alert__message">선택한 카드를 삭제할까요?</span>
        <div class="alert__util">
          <div class="alert__util-btn alert__util-btn--cancel">취소</div>
          <div class="alert__util-btn alert__util-btn--delete">삭제</div>
        </div>
      </div>
    </div>
    `;
}

function setEvents(cardID) {
  setCancelBtnEvent(cardID);
}

function setCancelBtnEvent(cardID) {
  const cancelBtn = document.querySelector(".alert__util-btn--cancel");
  cancelBtn.addEventListener("click", () => handleCancelBtnClickEvent(cardID));
}

function handleCancelBtnClickEvent(cardID) {
  Store.setCardTypeState(cardID, "normal");
}
