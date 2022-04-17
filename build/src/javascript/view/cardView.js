import { removeText, isTextLengthExceeded, hideElement } from "../util/util.js";
import {
  addServerCardData,
  updateServerCardData,
  deleteCardData,
} from "../controller/cardController.js";
import { addCardsDragEvent } from "./columnView.js";

export async function renderRegisterCard($cards) {
  const $allCards = document.querySelectorAll(".card");
  const todoCount = $allCards.length;
  const registerBoxTemp = `
    <div class="card"  id="card${todoCount + 1}">
      <div class="card-contents-wrapper row-sort">
        <div class="card-text-area">
          <div
            class="card-title title-font"
            contenteditable="true"
          >
            제목을 입력하세요
          </div>
          <div class="card-details"
          contentEditable="true">
            내용을 입력하세요
          </div>
        </div>
        <button class="card-cross-button">
          <img
            src="./image/crossBtn.svg"
            alt="cross-button-img"
            class="cross-button-img"
          />
        </button>
      </div>
      <div class="card-buttons-wrapper">
        <button class="cancel-button">취소</button>
        <button class="register-button">등록</button>
      </div>
    </div>
  `;
  $cards.insertAdjacentHTML("afterbegin", registerBoxTemp);
  const cardId = `#card${todoCount + 1}`;
  const $card = document.querySelector(cardId);
  const $crossBtn = $card.querySelector(".card-cross-button");
  hideElement($crossBtn);
  handleRegisterCardEvent($cards, $card);
}

function handleRegisterCardEvent($cards, $card) {
  const $cardTextArea = $card.querySelector(".card-text-area");
  const $registerBtn = $card.querySelector(".register-button");
  const $firstCard = $cards.firstElementChild;
  applyRegisterBoxStyle($firstCard);
  displayBtns($card);
  const $crossBtn = $card.querySelector(".card-cross-button");
  const $cancelBtn = $card.querySelector(".cancel-button");
  const $alert = document.querySelector("#alert-id");
  $cardTextArea.addEventListener("click", removeText);
  $registerBtn.addEventListener("click", updateCard);
  $cancelBtn.addEventListener("click", removeCard);
  $crossBtn.addEventListener("click", ({ target }) => {
    showAlert({ target }, $alert);
  });
}

export function addRegisterBtnsListener($column) {
  const $registerBtns = $column.querySelectorAll(".register-button");
  for (const $registerBtn of $registerBtns) {
    $registerBtn.addEventListener("click", updateCard);
  }
}

export function addCardDoubleClickEvent($cards) {
  $cards.addEventListener("dblclick", handleDoubleClickEvent);
}

function addBtnClickEvent($card) {
  const $editBtn = $card.querySelector(".edit-button");
  const $cancelBtn = $card.querySelector(".cancel-button");
  $editBtn.addEventListener("click", handleEditBtnEvent);
  $cancelBtn.addEventListener("click", handleCancelBtnEvent);
}

export function addDeleteEvent() {
  const $cancelBtn = document.querySelector("#alert-cancel-button");
  const $crossBtns = document.querySelectorAll(".card-cross-button");
  const $alert = document.querySelector("#alert-id");
  for (const $crossBtn of $crossBtns) {
    $crossBtn.addEventListener("click", ({ target }) => {
      showAlert({ target }, $alert);
    });
  }
  $cancelBtn.addEventListener("click", () => {
    hideElement($alert);
  });
}

function applyRegisterBoxStyle($card) {
  Object.assign($card.style, {
    border: "1px solid var(--blue)",
    opacity: 0.5,
  });
}

function displayBtns($card) {
  const $btnsWrpper = $card.querySelector(".card-buttons-wrapper");
  $btnsWrpper.style.display = "flex";
}

function updateCard({ target }) {
  const $selectedCard = target.closest(".card");
  applyCardStyle($selectedCard);
  setTextAreaContenteditable($selectedCard, false);
  addServerCardData($selectedCard);
  addCardsDragEvent($selectedCard);
  removeTextAreaEvent($selectedCard);
}

function removeTextAreaEvent($card) {
  const $textArea = $card.querySelector(".card-text-area");
  if ($textArea !== null) {
    console.log("이벤트사라짐");
    $textArea.removeEventListener("click", removeText);
  }
}

export function applyCardStyle($card) {
  const $btnWrapper = $card.querySelector(".card-buttons-wrapper");
  const $crossBtn = $card.querySelector(".card-cross-button");
  $crossBtn.style.display = "block";
  Object.assign($card.style, {
    opacity: 1,
    border: "none",
  });
  hideElement($btnWrapper);
}

function setTextAreaContenteditable($card, boolean) {
  const $cardTitle = $card.querySelector(".card-title");
  const $cardDetails = $card.querySelector(".card-details");
  $cardTitle.setAttribute("contenteditable", boolean);
  $cardDetails.setAttribute("contenteditable", boolean);
}

export function getUpdatedCardContent($selectedCard) {
  const $cardDetails = $selectedCard.querySelector(".card-details");
  const cardDetailsText = $cardDetails.innerText;
  if (isTextLengthExceeded(cardDetailsText)) {
    alert("글자 수가 초과했습니다 (500자)");
    return;
  }
  const $cardTitle = $selectedCard.querySelector(".card-title");
  const cardColumnName = $selectedCard.closest(".column").id;
  const cardTitleText = $cardTitle.innerText;
  const cardRegisterTime = Date.now();
  const card = {
    column: cardColumnName,
    title: cardTitleText,
    detail: cardDetailsText,
    createDate: cardRegisterTime,
  };
  return card;
}

function removeCard({ target }) {
  const $selectedCard = target.closest(".card");
  $selectedCard.remove();
}

function handleDoubleClickEvent({ target }) {
  const $clickedCard = target.closest(".card");
  if ($clickedCard) {
    displayBtns($clickedCard);
    applyRegisterBoxStyle($clickedCard);
    setTextAreaContenteditable($clickedCard, true);
    applyEditBtnStyle($clickedCard);
    addBtnClickEvent($clickedCard);
  }
}

function applyEditBtnStyle($card) {
  const $registerBtn = $card.querySelector(".register-button");
  if (!$registerBtn) return;
  $registerBtn.removeEventListener("click", updateCard);
  $registerBtn.className = "edit-button";
  $registerBtn.innerText = "수정";
}

function handleEditBtnEvent({ target }) {
  const $clickedCard = target.closest(".card");
  updateServerCardData($clickedCard);
  applyCardStyle($clickedCard);
  setTextAreaContenteditable($clickedCard, false);
}

function handleCancelBtnEvent({ target }) {
  const $clickedCard = target.closest(".card");
  applyCardStyle($clickedCard);
  setTextAreaContenteditable($clickedCard, false);
}

function showAlert({ target }, $alert) {
  $alert.style.display = "flex";
  const $selectedCard = target.closest(".card");
  const $editBtn = document.querySelector("#alert-edit-button");
  const handeDeleteEvent = function () {
    deleteCardData($selectedCard);
    removeCard({ target });
    hideElement($alert);
  };
  const bindFunc = handeDeleteEvent.bind({ target });
  $editBtn.addEventListener("click", bindFunc);
  $alert.addEventListener("mouseleave", () => {
    $editBtn.removeEventListener("click", bindFunc);
  });
}

export function changeOriginalCardStyle($originalCard) {
  applyCardStyle($originalCard);
  $originalCard.style.boxShadow = "";
}
