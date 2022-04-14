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

  TODO:
  인풋 인식 한 박자 느린거 고쳐야함!
*/

import { renderEmptyCard } from "../view/EmptyCardView.js";
import { renderCard } from "../view/CardView.js";
import { initColumn, onClickColumnAddBtn } from "../view/ColumnView.js";
import * as util from "../../util/Util.js";

const createMainController = (store) => {
  init(store);
};

function init(store) {
  const columns = document.querySelectorAll(".task-column");
  // add 버튼 이벤트 달기
  onClickColumnAddBtn(columns, handleAddBtn, store);
}

function handleAddBtn({ target }, store) {
  const parentColumn = target.closest(".task-column");
  const emptyCard = util.$("#empty-card", parentColumn);
  if (emptyCard) return;
  const handler = {
    inputHandler: handleCardInput,
    registerBtnHandler: handleRegisterBtn,
    removeBtnHandler: handleRemoveBtn,
  };
  renderEmptyCard(parentColumn, handler, store);
}

function handleCardInput({ target }) {
  const parentCard = target.closest(".task-card");
  const cardTitle = util.$(".task-card__title", parentCard);
  const cardContent = util.$(".task-card__content", parentCard);
  const cardTextData = [cardTitle, cardContent].map((input) => input.textContent.trim());
  const registerBtn = util.$(".task-card__register-btn.cursor-pointer", parentCard);

  if (cardTextData.includes("")) {
    registerBtn.disabled = true;
    return;
  }
  registerBtn.disabled = false;
}

async function handleRegisterBtn({ target }, store) {
  const parentCard = target.closest(".task-card");
  const cardTitle = parentCard.querySelector(".task-card__title").textContent;
  const cardContent = parentCard.querySelector(".task-card__content").textContent;

  // title, content을 바탕으로 state 만들기
  const newState = createCardState(cardTitle, cardContent);

  // 만든 state를 store에 저장(서버에 저장)
  const dataType = parentCard.parentElement.id;
  await store.addState(dataType, newState);

  // 클래스 리스트 추가 및 삭제
  // 삭제 버튼 & 더블 클릭 & 드래그드랍 이벤트 등록

  //   store.setState(inputValues);
  //   const isEmpty = inputValues.includes("");
  //   if (isEmpty) return;

  // const newState = getNewState(inputValues);

  // UTIL.fetchData(); // 포스트요청 보내야함
  // store.setState(newState);

  // 빈카드 삭제
}

function createCardState(cardTitle, cardContent) {
  return {
    id: Date.now(),
    title: cardTitle,
    content: cardContent,
  };
}

function handleRemoveBtn(event, store) {
  const { target } = event;
  target.closest(".task-card").remove();
  //store에서 데이터 삭제
  // 서버에 데이터 삭제 요청
}

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
