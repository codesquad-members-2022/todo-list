import * as util from "../../util/Util.js";

function renderCard(
  targetCard,
  removeBtnHandler,
  doubleClickTextHandler,
  dragDropHandler,
  store,
  dataType,
  newState
) {
  // 카드 렌더링
  renderRegisteredStyle(targetCard, newState);

  // 삭제 버튼 이벤트
  const deleteBtn = util.$(".delete-btn.cursor-pointer", targetCard);
  util.on("click", deleteBtn, (event) => {
    removeBtnHandler(event, store, dataType);
  });

  // 수정 이벤트
  util.on("dblclick", targetCard, (event) => {
    doubleClickTextHandler(event, store, dataType);
  });

  // 이동 이벤트
  // util.on("mousedown", targetCard, (event) => {
  //   dragDropHandler(event);
  // });
}

function renderRegisteredStyle(targetCard, newState) {
  targetCard.classList.add("registered");
  if (targetCard.id !== "empty-card") {
    util.$(".task-card__title", targetCard).textContent = newState.title;
    util.$(".task-card__content", targetCard).textContent = newState.content;
  }
  targetCard.id = newState.id;
  util.$(".delete-btn.cursor-pointer", targetCard).classList.remove("hidden");
  util
    .$(".task-card__cancle-btn.cursor-pointer", targetCard)
    .classList.add("hidden");
  util
    .$(".task-card__register-btn.cursor-pointer", targetCard)
    .classList.add("hidden");
  util.$(".task-card__footer", targetCard).classList.remove("hidden");
  util.$(".task-card__title", targetCard).contentEditable = false;
  util.$(".task-card__title", targetCard).classList.add("font-black");
  util.$(".task-card__content", targetCard).contentEditable = false;
  util.$(".task-card__content", targetCard).classList.add("font-black");
}

export { renderCard, renderRegisteredStyle };
