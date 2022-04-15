/*
  TODO:
- [ ] 드래그 앤 드랍

- [ ] 사이드 메뉴 이벤트
  - 카드 추가, 수정, 삭제, 이동 이벤트 핸들러에서 history data 저장
  - history data 가지고 렌더링
  - 시간 표기

- [ ] 칼럼 옆 카드 숫자 이벤트

< 리팩토링 >
- [ ] 수정 시 등록버튼의 text를 등록에서 수정으로 변경

- [ ] 삭제 버튼 클릭 시 class(style) 적용

- [ ] 카드에서 키 다운 인식 한 박자 느린거 수정
*/

import * as util from "../../util/Util.js";
import {
  renderEmptyCard,
  getRegisteredCardNode,
} from "../view/EmptyCardView.js";
import { renderCard, renderRegisteredStyle } from "../view/CardView.js";
import { onClickColumnAddBtn } from "../view/ColumnView.js";
import {
  renderHistoryCard,
  onClickMeunRemoveBtn,
} from "../view/historyView.js";

const createMainController = (store) => {
  init(store);
};

function init(store) {
  const columns = document.querySelectorAll(".task-column");
  // add 버튼 이벤트 달기
  onClickColumnAddBtn(columns, handleAddBtn, store);

  // 페이지 로드 후 렌더링 및 이벤트 부착

  ["todo", "doing", "done"].forEach((dataType) => {
    // documentfragment 만들어라
    const columnFragment = new DocumentFragment();

    store.getState(dataType).forEach((data) => {
      // 카드 템플릿을 만들어라
      const newCard = getRegisteredCardNode(data);
      // 이벤트를 부착해라
      util.on("keydown", newCard, handleCardInput);
      //// 등록 버튼 클릭 핸들링 이벤트
      const registerBtn = util.$(
        ".task-card__register-btn.cursor-pointer",
        newCard
      );
      util.on("click", registerBtn, (event) => {
        handleRegisterBtn(event, store);
      });
      //// 취소 버튼 클릭 핸들링 이벤트
      const removeBtn = util.$(
        ".task-card__cancle-btn.cursor-pointer",
        newCard
      );
      util.on("click", removeBtn, (event) => {
        handleCancleBtn(event, store);
      });
      //// 삭제 버튼 이벤트
      const deleteBtn = util.$(".delete-btn.cursor-pointer", newCard);
      util.on("click", deleteBtn, (event) => {
        handleRemoveBtn(event, store, dataType);
      });
      //// 수정 이벤트
      util.on("dblclick", newCard, (event) => {
        handleTextDoubleClick(event, store, dataType);
      });
      //// 이동 이벤트
      util.on("mousedown", newCard, (event) => {
        handleDragDrop(event, store);
      });
      // documentfragment에 붙여라
      columnFragment.insertBefore(newCard, columnFragment.childNodes[0]);
    });
    // dataType으로 컬럼 노드를 찾아라
    const columnTag = document.getElementById(dataType);
    // 컬럼 노드에 documentfragment 부착해라
    columnTag.appendChild(columnFragment);

    // 칼럼 옆 카드 숫자 표기
    const cardsCount = columnTag.querySelector(".column__card-cnt");
    cardsCount.textContent = columnTag.childElementCount - 1;
  });
  // store.getState("history").forEach((el) => {
  //   // 템플릿 만들고
  //   // 메뉴바에 붙이기
  // });
  onClickMeunRemoveBtn();
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
  const parentCardId = parentCard.id;
  const cardTitle = parentCard.querySelector(".task-card__title").textContent;
  const cardContent = parentCard.querySelector(
    ".task-card__content"
  ).textContent;
  const dataType = parentCard.parentElement.id;

  if (parentCardId !== "empty-card") {
    // 수정된 state 만들기
    const newState = createCardState(cardTitle, cardContent, parentCardId);
    // 수정된 state를 store 및 서버에 저장
    await store.updateState(dataType, parentCardId, newState);
    const cardHistory = extractCardState(parentCard, "update");
    const cardHistoryState = creatHistoryState(cardHistory);

    store.addState("history", cardHistoryState);
    renderHistoryCard(parentCard, cardHistoryState);
    renderRegisteredStyle(parentCard, newState);
  } else {
    // title, content을 바탕으로 state 만들기
    const newState = createCardState(cardTitle, cardContent);
    // 만든 state를 store 및 서버에 저장
    await store.addState(dataType, newState);
    const cardHistory = extractCardState(parentCard, "register");
    const cardHistoryState = creatHistoryState(cardHistory);

    store.addState("history", cardHistoryState);
    renderHistoryCard(parentCard, cardHistoryState);
    renderCard(
      parentCard,
      handleRemoveBtn,
      handleTextDoubleClick,
      handleDragDrop,
      store,
      dataType,
      newState
    );
    const parentColumn = target.closest(`#${dataType}`);
    const cardsCount = parentColumn.querySelector(".column__card-cnt");
    cardsCount.textContent++;
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

function handleRemoveBtn({ target }, store, dataType) {
  const cardToRemove = target.closest(".task-card");
  cardToRemove.classList.add("delete-active");

  const deleteConfirm = confirm("선택한 카드를 삭제할까요?");
  if (!deleteConfirm) {
    cardToRemove.classList.remove("delete-active");
    return;
  }

  store.removeState(dataType, cardToRemove.id);
  const cardState = extractCardState(cardToRemove, "remove");
  const historyState = creatHistoryState(cardState);
  store.addState("history", historyState);
  renderHistoryCard(cardToRemove, historyState);
  const parentColumn = cardToRemove.closest(`#${dataType}`);
  const cardsCount = parentColumn.querySelector(".column__card-cnt");
  cardsCount.textContent--;

  store.removeState(dataType, cardToRemove.id);
  cardToRemove.remove();
}

function handleTextDoubleClick({ target }, store, dataType) {
  // 등록 가능한 카드로 스타일 수정
  const targetCard = target.closest(".task-card");
  targetCard.classList.remove("registered");
  // targetCard.id = newState.id;
  util.$(".delete-btn.cursor-pointer", targetCard).classList.add("hidden");
  util
    .$(".task-card__cancle-btn.cursor-pointer", targetCard)
    .classList.remove("hidden");
  util
    .$(".task-card__register-btn.cursor-pointer", targetCard)
    .classList.remove("hidden");
  util.$(".task-card__footer", targetCard).classList.add("hidden");
  util.$(".task-card__title", targetCard).contentEditable = true;
  util.$(".task-card__title", targetCard).classList.remove("font-black");
  util.$(".task-card__content", targetCard).contentEditable = true;
  util.$(".task-card__content", targetCard).classList.remove("font-black");
  // 취소 버튼 클릭 시 등록된 카드로 스타일 변경
  // 등록 버튼 클릭 시 서버로 데이터 전송
}

function createCardState(cardTitle, cardContent, id) {
  return {
    id: +id || +Date.now(),
    title: cardTitle,
    content: cardContent,
  };
}

function creatHistoryState({ cardID, cardColumn, cardTitle, cardAction }) {
  return {
    id: cardID,
    column: cardColumn,
    title: cardTitle,
    action: cardAction,
  };
}

function extractCardState(card, action) {
  return {
    cardID: card.id,
    cardColumn: card.closest(".task-column"),
    cardTitle: card.querySelector(".task-card__title").textContent,
    cardAction: action,
  };
}
/* ~~~~~~~~~~~~~~~~~~~~~~~~~~ drag & drop ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
const handleDragDrop = (event, store) => {
  // const { target, pageX, pageY, clientX, clientY } = event;
  // const targetCard = target.closest(".task-card");
  // const cloneCard = targetCard.cloneNode(true);
  // target.closest(".task-column").appendChild(cloneCard);
  // cloneCard.style.position = "absolute";
  // let shiftX = clientX - targetCard.getBoundingClientRect().left;
  // let shiftY = clientY - targetCard.getBoundingClientRect().top;
  // const coord = { pageX, pageY, shiftX, shiftY };
  // moveAt(cloneCard, coord);
  // document.addEventListener("mousemove", (event) => {
  //   onMouseMove(event, shiftX, shiftY, cloneCard);
  // });
  // cloneCard.addEventListener("mouseup", (event) => {
  //   onMouseUp(targetCard, cloneCard);
  // });
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

function onMouseUp(targetCard, cloneCard) {
  document.removeEventListener("mousemove", onMouseMove);
  const cloneCardCoord = cloneCard.style.left + cloneCard.style.width / 2;
  const cloneCardCoordNum = cloneCardCoord.replace("p", "").replace("x", "");
  const todoColumn = util.$("#todo");
  const doingColumn = util.$("#doing");
  const doneColumn = util.$("#done");
  if (checkCoord(cloneCardCoordNum, todoColumn)) {
    /*이부분 left 수정해야함*/ // 왜 right는 안되는걸까?
    appendCard2Column(todoColumn, cloneCard);
    targetCard.remove();
    document.removeEventListener("mousemove", onMouseMove);
    return;
  }

  if (checkCoord(cloneCardCoordNum, doingColumn)) {
    appendCard2Column(doingColumn, cloneCard);
    targetCard.remove();
    document.removeEventListener("mousemove", onMouseMove);

    return;
  }

  if (checkCoord(cloneCardCoordNum, doneColumn)) {
    appendCard2Column(doneColumn, cloneCard);
    targetCard.remove();

    return;
  }
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
