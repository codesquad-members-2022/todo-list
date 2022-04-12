import { renderEmptyCard } from "../view/EmptyCardView.js";
import { renderCard } from "../view/CardView.js";
import { initColumn, onClickColumnAddBtn } from "../view/ColumnView.js";

function addCard2Column(event, store) {
  const { target } = event;
  const parentColumn = target.closest(".column__zone");
  const hasEmptyCard = parentColumn.querySelector(".testNewCard");
  if (hasEmptyCard) return;
  renderEmptyCard(parentColumn, createCardState, store);
}

async function createCardState(event, store) {
  const { target } = event;
  const parentCard = target.closest(".testNewCard");
  const inputValues = [...parentCard.querySelectorAll("input")].map(
    (input) => input.value
  );

  const isEmpty = inputValues.includes("");

  if (isEmpty) return;

  // const newState = getNewState(inputValues);

  // UTIL.fetchData(); // 포스트요청 보내야함
  // store.setState(newState);

  // //빈카드 삭제
  // renderCard(데이터,삭제이벤트,더블클릭이벤트,이동이벤트,store)
}

function removeCard(event) {
  const { target } = event;
  target.closest(".task-card").remove();
}

function init(store) {
  const columnZone = document.querySelector(".column__zone");
  initColumn();
  onClickColumnAddBtn(columnZone, addCard2Column, store);
}

const createColumnController = (store) => {
  const columnStore = store;
  init(columnStore);
};

export { createColumnController };
