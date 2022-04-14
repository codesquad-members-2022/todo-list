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
  const cardTextData = [cardTitle, cardContent].map((input) =>
    input.textContent.trim()
  );
  const registerBtn = util.$(
    ".task-card__register-btn.cursor-pointer",
    parentCard
  );

  if (cardTextData.includes("")) {
    registerBtn.disabled = true;
    return;
  }
  registerBtn.disabled = false;
}

async function handleRegisterBtn(event, store) {
  const { target } = event;
  event.stopPropagation();
  const parentCard = target.closest(".task-card");
  const cardTitle = parentCard.querySelector(".task-card__title").textContent;
  const cardContent = parentCard.querySelector(
    ".task-card__content"
  ).textContent;

  // title, content을 바탕으로 state 만들기
  const newState = createCardState(cardTitle, cardContent);

  // 만든 state를 store에 저장(서버에 저장)
  const dataType = parentCard.parentElement.id;
  await store.addState(dataType, newState);

  // 클래스 리스트 추가 및 삭제 & 이벤트 등록
  // 더블 클릭 수정 이벤트
  // 드래그 드랍 이벤트
  // 삭제 버튼 이벤트
  renderCard(parentCard, dragDrop);
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
  //서버에 데이터 삭제 요청
}

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~ drag & drop ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
const dragDrop = (event) => {
  const { target, pageX, pageY, clientX, clientY } = event;
  const targetCard = target.closest(".task-card");
  const cloneCard = targetCard.cloneNode(true);
  target.closest(".task-column").appendChild(cloneCard);

  cloneCard.style.position = "absolute";

  let shiftX = clientX - targetCard.getBoundingClientRect().left;
  let shiftY = clientY - targetCard.getBoundingClientRect().top;

  const coord = { pageX, pageY, shiftX, shiftY };

  moveAt(cloneCard, coord);

  cloneCard.addEventListener("mouseup", (event) => {
    onMouseUp(event, shiftX, shiftY, cloneCard);
  });

  document.addEventListener("mousemove", (event) => {
    onMouseMove(event, shiftX, shiftY, cloneCard);
  });
};

function moveAt(cloneCard, { pageX, pageY, shiftX, shiftY }) {
  Object.assign(cloneCard.style, {
    left: pageX - shiftX + "px",
    top: pageY - shiftY + "px",
  });
}

function onMouseMove(event, shiftX, shiftY, cloneCard) {
  const { pageX, pageY } = event;
  moveAt(cloneCard, { pageX, pageY, shiftX, shiftY });
}

function onMouseUp(event, shiftX, shiftY, cloneCard) {
  document.removeEventListener("mousemove", onMouseMove);
  const cloneCardCoord = cloneCard.style.left + cloneCard.style.width / 2;
  const cloneCardCoordNum = cloneCardCoord.replace("p", "").replace("x", "");
  const todoColumn = util.$("#todo");
  const doingColumn = util.$("#doing");
  const doneColumn = util.$("#done");
  if (checkCoord(cloneCardCoordNum, todoColumn)) {
    /*이부분 left 수정해야함*/ // 왜 right는 안되는걸까?
    appendCard2Column(todoColumn, cloneCard);
    return;
  }

  if (checkCoord(cloneCardCoordNum, doingColumn)) {
    appendCard2Column(doingColumn, cloneCard);
    return;
  }

  if (checkCoord(cloneCardCoordNum, doneColumn)) {
    appendCard2Column(doneColumn, cloneCard);
    return;
  }

  // cloneCard.remove();
}

function appendCard2Column(targetColumn, targetCard) {
  targetColumn.appendChild(targetCard);
  Object.assign(targetCard.style, {
    position: "static",
    left: "0px",
    top: "0px",
  });
}

function checkCoord(targetCardCoord, targetColumnCoord) {
  return (
    targetCardCoord > targetColumnCoord.getBoundingClientRect().left &&
    targetColumnCoord.getBoundingClientRect().right > targetCardCoord
  );
}
export { createMainController };
