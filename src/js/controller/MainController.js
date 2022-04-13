/*
  TODO:
  - [ ] 초기 화면 카드 그리기
  - column
    - [x] 추가 버튼 이벤트
    - [ ] 삭제 버튼 이벤트
  - card
    - [ ] 삭제 버튼 이벤트
    - [ ] 수정 이벤트
    - [ ] 이동 이벤트
  - [ ] 사이드 메뉴 이벤트
  */

import { renderEmptyCard } from "../view/EmptyCardView.js";
import { renderCard } from "../view/CardView.js";
import { initColumn, onClickColumnAddBtn } from "../view/ColumnView.js";
import * as util from "../../util/Util.js";

function init(store) {
  const columns = document.querySelectorAll(".task-column");
  // add 버튼 이벤트 달기
  onClickColumnAddBtn(columns, handleClickAddBtn, store);
}

function handleClickAddBtn({ target }, store) {
  const parentColumn = target.closest(".task-column");
  const emptyCard = util.$("#empty-card", parentColumn);
  if (emptyCard) return;
  renderEmptyCard(parentColumn, handleClickRegisterBtn, store);
}

async function handleClickRegisterBtn({ target }, store) {
  const parentCard = target.closest(".task-card");
  console.log(parentCard);
  const inputValues = [
    parentCard.querySelector(".task-card__title"),
    parentCard.querySelector(".task-card__content"),
  ].map((input) => input.textContent);

  //   const isEmpty = inputValues.includes("");
  //   if (isEmpty) return;

  // const newState = getNewState(inputValues);

  // UTIL.fetchData(); // 포스트요청 보내야함
  // store.setState(newState);

  // //빈카드 삭제
  //renderCard(데이터, 삭제이벤트, 더블클릭이벤트, 이동이벤트, store);
}

function removeCard(event) {
  const { target } = event;
  target.closest(".task-card").remove();
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
