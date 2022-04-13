import { removeText } from "../util/util.js";
import { addCardDoubleClickEvent } from "./dblClickView.js";
import { addServerCardData } from "../controller/cardController.js";
import { showAlert } from "./deleteCardView.js";

async function init() {
  addPlusBtnEvent();
}
function addPlusBtnEvent() {
  const $plusBtn = document.querySelector("#have-to-do-plus");
  $plusBtn.addEventListener("click", renderRegisterCard);
}

async function renderRegisterCard() {
  const $cards = document.querySelector("#have-to-do-cards");
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
  $crossBtn.style.display = "none";
  handleRegisterCardEvent($cards, $card);
}

function handleRegisterCardEvent($cards, $card) {
  const $cardTextArea = $card.querySelector(".card-text-area");
  const $registerBtn = $card.querySelector(".register-button");
  const $firstCard = $cards.firstElementChild;
  applyRegisterBoxStyle($firstCard);
  displayButtons($card);
  const $crossBtn = $card.querySelector(".card-cross-button");
  const $cancelBtn = $card.querySelector(".cancel-button");
  $cardTextArea.addEventListener("click", removeText);
  $registerBtn.addEventListener("click", updateCard);
  $cancelBtn.addEventListener("click", removeCard);
  $crossBtn.addEventListener("click", showAlert);
}

function displayButtons($card) {
  const $buttonWrpper = $card.querySelector(".card-buttons-wrapper");
  $buttonWrpper.style.display = "flex";
}

function applyRegisterBoxStyle($card) {
  Object.assign($card.style, {
    border: "1px solid var(--blue)",
    opacity: 0.5,
  });
}

function updateCard({ target }) {
  const $selectedCard = target.closest(".card");
  applyCardStyle($selectedCard);
  setTextAreaContenteditable($selectedCard, false);
  addServerCardData($selectedCard);
}

function getUpdatedCardContent(target) {
  const $selectedCard = target.closest(".card");
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

function isTextLengthExceeded(text) {
  const textLength = text.length;
  const maxLength = 500;
  return textLength > maxLength;
}

function applyCardStyle($card) {
  const $buttonWrapper = $card.querySelector(".card-buttons-wrapper");
  const $crossBtn = $card.querySelector(".card-cross-button");
  $crossBtn.style.display = "block";
  Object.assign($card.style, {
    opacity: 1,
    border: "none",
  });
  $buttonWrapper.style.display = "none";
}

function setTextAreaContenteditable($card, boolean) {
  const $cardTitle = $card.querySelector(".card-title");
  const $cardDetails = $card.querySelector(".card-details");
  $cardTitle.setAttribute("contenteditable", boolean);
  $cardDetails.setAttribute("contenteditable", boolean);
}

function renderColumn(columnId, todos) {
  const $column = document.querySelector(columnId);
  const $cards = $column.querySelector(".cards");
  const cardTemplate = todos.reduce((template, todo) => {
    template += `<div class="card" id="card${todo.id}">
    <div class="card-contents-wrapper row-sort">
      <div class="card-text-area">
        <div class="card-title title-font">${todo.title}</div>
        <div class="card-details">${todo.detail}</div>
      </div>
      <figure class="card-cross-button">
        <img
          src="./image/crossBtn.svg"
          alt="cross-button-img"
          class="cross-button-img"
        />
      </figure>
    </div>
    <div class="card-buttons-wrapper">
      <button class="cancel-button">취소</button>
      <button class="register-button">등록</button>
    </div>
  </div>`;
    return template;
  }, "");
  $cards.insertAdjacentHTML("afterbegin", cardTemplate);
  addRegisterBtnsListener($column);
  addCardDoubleClickEvent($cards);
}

function addRegisterBtnsListener($column) {
  const $registerBtns = $column.querySelectorAll(".register-button");
  for (const $registerBtn of $registerBtns) {
    $registerBtn.addEventListener("click", updateCard);
  }
}

export function removeCard({ target }) {
  const $selectedCard = target.closest(".card");
  $selectedCard.remove();
}

export {
  init,
  renderColumn,
  applyRegisterBoxStyle,
  applyCardStyle,
  updateCard,
  getUpdatedCardContent,
  setTextAreaContenteditable,
};
