import { removeText, axiosRequest } from "../util/util.js";

function init() {
  addPlusBtnEvent();
}
function addPlusBtnEvent() {
  const $plusBtn = document.querySelector("#have-to-do-plus");
  $plusBtn.addEventListener("click", renderRegisterCard);
}

async function renderRegisterCard() {
  const $cards = document.querySelector("#have-to-do-cards");
  const todoCount = $cards.children.length;
  const registerBoxTemp = `
    <div class="card"  id="card${todoCount + 1}">
      <div class="card-contents-wrapper row-sort">
        <div class="card-text-area">
          <div
            class="card-title title-font"
            contentEditable="true"
          >
            제목을 입력하세요
          </div>
          <div class="card-details"  contentEditable="true">
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
        <button class="cancel-button center-sort">취소</button>
        <button class="register-button center-sort">등록</button>
      </div>
    </div>
  `;
  $cards.insertAdjacentHTML("afterbegin", registerBoxTemp);
  const cardId = `#card${todoCount + 1}`;
  const $card = document.querySelector(cardId);
  handleRegisterCardEvent($cards, $card);
}

function handleRegisterCardEvent($cards, $card) {
  const $cardTextArea = $card.querySelector(".card-text-area");
  const $registerBtn = $card.querySelector(".register-button");
  changeRegisterBoxStyle($cards);
  $cardTextArea.addEventListener("click", removeText);
  $registerBtn.addEventListener("click", updateCard);
}

function changeRegisterBoxStyle($cards) {
  const $registerCard = $cards.firstElementChild;
  Object.assign($registerCard.style, {
    border: "1px solid var(--blue)",
    opacity: 0.5,
  });
}

function updateCard({ target }) {
  const $selectedCard = target.closest(".card");
  const $cardDetails = $selectedCard.querySelector(".card-details");
  const $cardTitle = $selectedCard.querySelector(".card-title");
  const card = updateCardContents(target);
  applyCardStyle($selectedCard);
  preventModification($cardTitle, $cardDetails);
  axiosRequest("post", "todos", card);
  return card;
}

function updateCardContents(target) {
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
    time: cardRegisterTime,
  };
  return card;
}

function isTextLengthExceeded(text) {
  const textLength = text.length;
  const maxLength = 500;
  return textLength > maxLength;
}

function applyCardStyle($card) {
  const $buttonWrpper = $card.querySelector(".card-buttons-wrapper");
  Object.assign($card.style, {
    opacity: 1,
    border: "none",
  });
  Object.assign($buttonWrpper.style, {
    display: "none",
  });
}

function preventModification($cardTitle, $cardDetails) {
  $cardTitle.setAttribute("contenteditable", "false");
  $cardDetails.setAttribute("contenteditable", "false");
}

function renderColumn(columnId, todos) {
  const $column = document.querySelector(columnId);
  const $cards = $column.querySelector(".cards");
  const cardTemplate = todos.reduce((template, todo) => {
    template += `<div class="card" id="card${todo.id}">
    <div class="card-contents-wrapper row-sort">
      <div class="card-text-area">
        <div class="card-title title-font">${todo.title}</div>
        <div class="card-details" contenteditable="true">${todo.detail}</div>
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
      <button class="cancel-button center-sort">취소</button>
      <button class="register-button center-sort">등록</button>
    </div>
  </div>`;
    return template;
  }, "");
  $cards.insertAdjacentHTML("afterbegin", cardTemplate);
  addRegisterBtnsListener($column);
}

function addRegisterBtnsListener($column) {
  const $registerBtns = $column.querySelectorAll(".register-button");
  for (const $registerBtn of $registerBtns) {
    $registerBtn.addEventListener("click", updateCard);
  }
}

export { init, renderColumn, updateCard };
