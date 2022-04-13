import { renderEmptyCard } from "../view/EmptyCardView.js";
import { renderCard } from "../view/CardView.js";
import { initColumn, onClickColumnAddBtn } from "../view/ColumnView.js";

function addCard2Column(event, store) {
  const { target } = event;
  const parentColumn = target.closest(".task-column");
  const hasEmptyCard = parentColumn.querySelector(".testNewCard");
  if (hasEmptyCard) return;
  renderEmptyCard(parentColumn, createCardState, store);
}

function checkInput({ target }) {
  const parent = target.closest(".task-card");
  const title = parent.querySelector(".task-card__title");
  const content = parent.querySelector(".task-card__content");
  const inputs = [title, content].map((input) => input.textContent);
  if (inputs.includes("")) return;

  parent.querySelector(
    ".task-card__register-btn.cursor-pointer"
  ).disabled = false;
}

async function createCardState(event, store) {
  const { target } = event;
  const parentCard = target.closest(".task-card");
  const inputValues = [
    parentCard.querySelector(".task-card__title"),
    parentCard.querySelector(".task-card__content"),
  ].map((input) => input.textContent);

  //   store.setState(inputValues);

  //   const isEmpty = inputValues.includes("");
  //   if (isEmpty) return;

  // const newState = getNewState(inputValues);

  // UTIL.fetchData(); // 포스트요청 보내야함
  // store.setState(newState);

  // 빈카드 삭제
  renderCard(inputValues, removeCard, changeCardState, dragDrop, store);
}

function removeCard(event, store) {
  const { target } = event;
  target.closest(".task-card").remove();
  //store에서 데이터 삭제
  // 서버에 데이터 삭제 요청
}

function changeCardState(event, store) {}

function init(store) {
  const columnZone = document.querySelector(".task-column");
  //   initColumn();
  onClickColumnAddBtn(columnZone, addCard2Column, checkInput, store);
}

const createMainController = (store) => {
  init(store);
};

const dragDrop = (event) => {
  const movingBox = document.createElement("div");
  movingBox.classList.add("box");
  movingBox.style.position = "absolute";
  document.querySelector(".zone").appendChild(movingBox); // 이부분 수정필요

  const { target, pageX, pageY, clientX, clientY } = event;
  let shiftX = clientX - target.getBoundingClientRect().left;
  let shiftY = clientY - target.getBoundingClientRect().top;
  const coord = { pageX, pageY, shiftX, shiftY };
  moveAt(movingBox, coord);

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener("mousemove", onMouseMove);
};

function moveAt(element, { pageX, pageY, shiftX, shiftY }) {
  Object.assign(element.style, {
    left: pageX - shiftX + "px",
    top: pageY - shiftY + "px",
  });
}

function onMouseMove() {
  document.removeEventListener("mousemove", onMouseMove);
  const movingBoxCoord = movingBox.style.left + movingBox.style.width / 2;

  if (movingBoxCoord > document.querySelectorAll(".zone")[2].style.left) {
    /*이부분 left 수정해야함*/ // 왜 right는 안되는걸까?
    document.querySelectorAll(".zone")[2].appendChild(movingBox);
    Object.assign(movingBox.style, {
      position: "static",
      left: "0px",
      top: "0px",
    });

    return;
  }

  if (movingBoxCoord > document.querySelectorAll(".zone")[1].style.left) {
    document.querySelectorAll(".zone")[1].appendChild(movingBox);
    Object.assign(movingBox.style, {
      position: "static",
      left: "0px",
      top: "0px",
    });

    return;
  }

  movingBox.remove();
}

export { createMainController };
