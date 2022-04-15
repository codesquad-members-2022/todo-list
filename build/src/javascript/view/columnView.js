import { putUpdatedCardData } from "../model/cardModel.js";
import { debounce, moveAt } from "../util/util.js";
import {
  renderRegisterCard,
  addRegisterBtnsListener,
  addCardDoubleClickEvent,
  changeOriginalCardStyle,
  getUpdatedCardContent,
} from "./cardView.js";

let [isDraggable, dragStart, isDowned] = [false, false, false];
let [shiftX, shiftY] = [0, 0];
let $originalCard;

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

export function addPlusBtnEvent() {
  const $columns = document.querySelectorAll(".column");
  $columns.forEach(($column) => {
    const $plusBtn = $column.querySelector(".plus-button");
    const $cards = $column.querySelector(".cards");
    $plusBtn.addEventListener("click", () => {
      renderRegisterCard($cards);
    });
  });
}

function addCardsDragEvent($cards) {
  const cardsList = $cards.querySelectorAll(".card");
  for (const card of cardsList) {
    addCardDragEvent(card);
  }
}

export function addCardDragEvent(card) {
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

export function addmouseMoveEvent() {
  document.body.addEventListener("mousemove", handleMouseMoveEvent);
}

function addCopiedCardEvent() {
  const $copiedCard = document.querySelector(".copied-card");
  $copiedCard.addEventListener("mouseup", () => {
    isDowned = false;
    dragStart = false;
    isDraggable = false;
    $copiedCard.remove();
    changeOriginalCardStyle($originalCard);
    updateDraggedCardData();
    $originalCard.classList.remove("original-card");
  });
}

function handleMouseMoveEvent(event) {
  const delayTime = 20;
  const columnsLocation = getColumnsLocation();
  const $card = event.target.closest(".card");
  if (isDraggable && dragStart && $card) {
    $originalCard = $card;
    makeDragElement($card);
    applyPlaceStyle($card);
    dragStart = false;
    addCopiedCardEvent();
  }
  if (isDraggable && $card) {
    debounce(function () {
      moveCopiedCard(event, columnsLocation, $originalCard);
    }, delayTime)();
  }
}

function getColumnsLocation() {
  const columnsArr = document.querySelectorAll(".column");
  const columnsLocation = [];
  for (const column of columnsArr) {
    columnsLocation.push(column.getBoundingClientRect());
  }
  return columnsLocation;
}

function makeDragElement(element) {
  const copiedElement = element.cloneNode(true);
  element.classList.add("original-card");
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

function moveCopiedCard(event, columnsLocation, $originalCard) {
  const $copiedCard = document.querySelector(".copied-card");
  if (!$copiedCard) return;
  shiftX = event.pageX - $copiedCard.offsetWidth / 2;
  shiftY = event.pageY - $copiedCard.offsetHeight / 2;
  moveAt($copiedCard, shiftX, shiftY);
  movePlaceCard(columnsLocation, $originalCard);
}

function movePlaceCard(columnsLocation, $originalCard) {
  const currentCards = checkHorizontalMove(columnsLocation, $originalCard);
  checkVerticalMove(currentCards, $originalCard);
}

function checkHorizontalMove(columnsLocation, $originalCard) {
  const [_, doingColumn, doneColumn] = columnsLocation;
  const $originalCards = $originalCard.closest(".cards");
  let currentCards;
  if (shiftX > doingColumn.x && shiftX < doneColumn.x) {
    currentCards = checkColumn($originalCards, $originalCard, "#doing-cards");
  } else if (shiftX > doneColumn.x) {
    currentCards = checkColumn($originalCards, $originalCard, "#done-cards");
  } else {
    currentCards = checkColumn(
      $originalCards,
      $originalCard,
      "#have-to-do-cards"
    );
  }
  return currentCards;
}

function checkColumn($originalCards, $originalCard, cardsID) {
  const $currentCards = document.querySelector(cardsID);
  if ($currentCards !== $originalCards) {
    $currentCards.appendChild($originalCard);
  }
  return $currentCards;
}

function checkVerticalMove(currentCards, $originalCard) {
  const currentCardsY = currentCards.getBoundingClientRect().y;
  const cardCount = currentCards.children.length;
  const cardHeight = $originalCard.offsetHeight;
  const cardMargin = 16;
  const cardAreaHeight = cardHeight + cardMargin;
  const diffY = shiftY - currentCardsY;
  const prevCardCount = Math.floor(diffY / cardAreaHeight);
  if (prevCardCount <= cardCount && prevCardCount >= 1) {
    const prevCardSelector = `.card:nth-child(${prevCardCount})`;
    const $prevCard = currentCards.querySelector(prevCardSelector);
    $prevCard.after($originalCard);
  } else if (prevCardCount < 1) {
    currentCards.insertAdjacentElement("afterbegin", $originalCard);
  }
}

function updateDraggedCardData() {
  const cardData = getUpdatedCardContent($originalCard);
  const dataID = $originalCard.id.slice(4);
  putUpdatedCardData(cardData, dataID);
}
