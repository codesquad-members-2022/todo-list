/*
  TODO:
- [ ] 초기 화면 카드 그리기 - 올리버

- [ ] 드래그 앤 드랍 - 도트

- [ ] 사이드 메뉴 이벤트 - 올리버
  - 카드 추가, 수정, 삭제, 이동 이벤트 핸들러에서 history data 저장
  - history data 가지고 렌더링
  - 시간 표기

- [ ] 칼럼 옆 카드 숫자 이벤트 - 도트


< 리팩토링 >
- [ ] 수정 시 등록버튼의 text를 등록에서 수정으로 변경

- [ ] 삭제 버튼 클릭 시 class(style) 적용

- [ ] 카드에서 키 다운 인식 한 박자 느린거 수정
*/

import { renderEmptyCard } from "../view/EmptyCardView.js";
import { renderCard, renderRegisteredStyle } from "../view/CardView.js";
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
    cancleBtnHandler: handleCancleBtn,
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
  const parentCardId = parentCard.id;
  const cardTitle = parentCard.querySelector(".task-card__title").textContent;
  const cardContent = parentCard.querySelector(".task-card__content").textContent;
  const dataType = parentCard.parentElement.id;

  if (parentCardId !== "empty-card") {
    // 수정된 state 만들기
    const newState = createCardState(cardTitle, cardContent, parentCardId);
    // 수정된 state를 store 및 서버에 저장
    await store.updateState(dataType, parentCardId, newState);
    renderRegisteredStyle(parentCard, newState);
  } else {
    // title, content을 바탕으로 state 만들기
    const newState = createCardState(cardTitle, cardContent);
    // 만든 state를 store 및 서버에 저장
    await store.addState(dataType, newState);
    renderCard(parentCard, handleRemoveBtn, handleTextDoubleClick, store, dataType, newState);
  }
}

function handleCancleBtn({ target }, store) {
  const parentCard = target.closest(".task-card");
  const parentCardId = parentCard.id;
  const dataType = parentCard.parentElement.id;
  if (parentCardId !== "empty-card") {
    // 현재 스토어에 있는 tittle content를 가져와서 다시 렌더링해야함.
    let cardTitle = "";
    let cardContent = "";
    store.getState(dataType).forEach((el, idx) => {
      if (el.id === +parentCardId) {
        cardTitle = el.title;
        cardContent = el.content;
      }
    });
    const newState = createCardState(cardTitle, cardContent, parentCardId);
    renderRegisteredStyle(parentCard, newState);
  } else {
    parentCard.remove();
  }
}

function createCardState(cardTitle, cardContent, id) {
  return {
    id: +id || +Date.now(),
    title: cardTitle,
    content: cardContent,
  };
}

function handleRemoveBtn({ target }, store, dataType) {
  const cardToRemove = target.closest(".task-card");
  cardToRemove.classList.add("delete-active");

  const deleteConfiem = confirm("선택한 카드를 삭제할까요?");
  if (!deleteConfiem) {
    cardToRemove.classList.remove("delete-active");
    return;
  }

  store.removeState(dataType, cardToRemove.id);
  cardToRemove.remove();
}

function handleTextDoubleClick({ target }, store, dataType) {
  // 등록 가능한 카드로 스타일 수정
  const targetCard = target.closest(".task-card");
  targetCard.classList.remove("registered");
  // targetCard.id = newState.id;
  util.$(".delete-btn.cursor-pointer", targetCard).classList.add("hidden");
  util.$(".task-card__cancle-btn.cursor-pointer", targetCard).classList.remove("hidden");
  util.$(".task-card__register-btn.cursor-pointer", targetCard).classList.remove("hidden");
  util.$(".task-card__footer", targetCard).classList.add("hidden");
  util.$(".task-card__title", targetCard).contentEditable = true;
  util.$(".task-card__title", targetCard).classList.remove("font-black");
  util.$(".task-card__content", targetCard).contentEditable = true;
  util.$(".task-card__content", targetCard).classList.remove("font-black");
  // 취소 버튼 클릭 시 등록된 카드로 스타일 변경
  // 등록 버튼 클릭 시 서버로 데이터 전송
}

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~ drag & drop ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
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
