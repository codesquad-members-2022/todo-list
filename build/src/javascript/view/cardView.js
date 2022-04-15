import {
  removeText,
  isTextLengthExceeded,
  hideElement,
  throttle,
  debounce,
} from "../util/util.js";
import {
  addServerCardData,
  updateServerCardData,
  deleteCardData,
} from "../controller/cardController.js";

let [isDraggable, dragStart, isDowned] = [false, false, false];
let [shiftX, shiftY] = [0, 0];

export function addPlusBtnEvent() {
  const $plusBtn = document.querySelector("#have-to-do-plus");
  const $cards = document.querySelector("#have-to-do-cards");
  $plusBtn.addEventListener("click", () => {
    renderRegisterCard($cards);
  });
}

async function renderRegisterCard($cards) {
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

function applyCardStyle($card) {
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

export function renderColumn(columnId, todos) {
  const columnSelector = `#${columnId}`;
  const $column = document.querySelector(columnSelector);
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
  addCardsDragEvent($cards);
}
function getColumnsLocation() {
  const columnsArr = document.querySelectorAll(".column");
  const columnsLocation = [];
  for (const column of columnsArr) {
    columnsLocation.push(column.getBoundingClientRect());
  }
  return columnsLocation;
}
export function addmouseMoveEvent() {
  const delayTime = 20;
  const columnsLocation = getColumnsLocation();
  let $originalCard;
  document.body.addEventListener("mousemove", (event) => {
    const $card = event.target.closest(".card");
    if (isDraggable && $card && dragStart) {
      $originalCard = $card;
      makeDragElement($card);
      applyPlaceStyle($card);
      dragStart = false;
      addCopiedCardEvent();
    }
    if ($card && isDraggable) {
      debounce(function () {
        moveCopiedCard(event, columnsLocation, $originalCard);
      }, delayTime)();
    }
  });
}

function moveCopiedCard(event, columnsLocation, $originalCard) {
  const $copiedCard = document.querySelector(".copied-card");
  if (!$copiedCard) return;
  shiftX = event.pageX - $copiedCard.offsetWidth / 2;
  shiftY = event.pageY - $copiedCard.offsetHeight / 2;
  moveAt($copiedCard);
  movePlaceCard(columnsLocation, $originalCard);
}

function movePlaceCard(columnsLocation, $originalCard) {
  const $originalCards = $originalCard.closest(".cards");
  const currentCardColumn = checkHorizontalMove(
    columnsLocation,
    $originalCard,
    $originalCards
  );
  checkVerticalMove(currentCardColumn, $originalCard);
}

function checkHorizontalMove(columnsLocation, $originalCard, $originalCards) {
  const [_, doingColumn, doneColumn] = columnsLocation;
  if (shiftX > doingColumn.x && shiftX < doneColumn.x) {
    const $doingCards = document.querySelector("#doing-cards");
    if ($doingCards === $originalCards) return $doingCards;
    $doingCards.appendChild($originalCard);
    return $doingCards;
  } else if (shiftX > doneColumn.x) {
    const $doneCards = document.querySelector("#done-cards");
    if ($doneCards === $originalCards) return $doneCards;
    $doneCards.appendChild($originalCard);
    return $doneCards;
  } else {
    const $haveTodoCards = document.querySelector("#have-to-do-cards");
    if ($haveTodoCards === $originalCards) return $haveTodoCards;
    $haveTodoCards.appendChild($originalCard);
    return $haveTodoCards;
  }
}

function checkVerticalMove(currentCardColumn, $originalCard) {
  const currentCardsY = currentCardColumn.getBoundingClientRect().y;
  const cardCount = currentCardColumn.children.length;
  const cardHeight = $originalCard.offsetHeight;
  const cardMargin = 16;
  const cardAreaHeight = cardHeight + cardMargin;
  const diffY = shiftY - currentCardsY;
  const prevCardCount = Math.floor(diffY / cardAreaHeight);
  if (prevCardCount <= cardCount) {
    const prevCardSelector = `.card:nth-child(${prevCardCount})`;
    const $prevCard = currentCardColumn.querySelector(prevCardSelector);
    $prevCard.after($originalCard);
  }
}

function addCopiedCardEvent() {
  const $copiedCard = document.querySelector(".copied-card");
  $copiedCard.addEventListener("mouseup", () => {
    isDowned = false;
    dragStart = false;
    isDraggable = false;
    $copiedCard.remove();
  });
}

function moveAt(element) {
  Object.assign(element.style, {
    top: `${shiftY}px`,
    left: `${shiftX}px`,
  });
}

function addCardsDragEvent($cards) {
  const cardsList = $cards.querySelectorAll(".card");

  for (const card of cardsList) {
    card.addEventListener("mousedown", (event) => {
      const $card = event.target.closest(".card");
      shiftX = event.pageX - $card.offsetWidth / 2;
      shiftY = event.pageY - $card.offsetHeight / 2;
      isDowned = true;
      dragStart = true;
    });
    card.addEventListener("mousemove", () => {
      if (isDowned) {
        isDraggable = true;
      }
    });
  }
}

function makeDragElement(element) {
  const copiedElement = element.cloneNode(true);
  copiedElement.classList.add("copied-card");
  Object.assign(copiedElement.style, {
    position: "absolute",
    zIndex: 10,
    top: `${shiftY}px`,
    left: `${shiftX}px`,
    opacity: 0.8,
    boxShadow:
      "0px 0px 4px rgba(204, 204, 204, 0.5), 0px 2px 4px rgba(0, 0, 0, 0.25)",
    backdropFilter: "blur(4px)",
    margin: 0,
  });
  document.body.appendChild(copiedElement);
}

function applyPlaceStyle(element) {
  Object.assign(element.style, {
    opacity: 0.4,
    border: "1px solid var(--blue)",
    boxShadow: "0px 1px 30px rgba(224, 224, 224, 0.3)",
  });
}

function addRegisterBtnsListener($column) {
  const $registerBtns = $column.querySelectorAll(".register-button");
  for (const $registerBtn of $registerBtns) {
    $registerBtn.addEventListener("click", updateCard);
  }
}

function addCardDoubleClickEvent($cards) {
  $cards.addEventListener("dblclick", handleDoubleClickEvent);
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

function displayBtns($card) {
  const $btnsWrpper = $card.querySelector(".card-buttons-wrapper");
  $btnsWrpper.style.display = "flex";
}

function applyEditBtnStyle($card) {
  const $registerBtn = $card.querySelector(".register-button");
  if (!$registerBtn) return;
  $registerBtn.removeEventListener("click", updateCard);
  $registerBtn.className = "edit-button";
  $registerBtn.innerText = "수정";
}

function addBtnClickEvent($card) {
  const $editBtn = $card.querySelector(".edit-button");
  const $cancelBtn = $card.querySelector(".cancel-button");
  $editBtn.addEventListener("click", handleEditBtnEvent);
  $cancelBtn.addEventListener("click", handleCancelBtnEvent);
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

function removeCard({ target }) {
  const $selectedCard = target.closest(".card");
  $selectedCard.remove();
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
