import * as util from "../../util/Util.js";

function renderCard(targetCard, dragDropHandler, callbackChangeEvt) {
  renderRegisteredStyle(targetCard);
  util.on("click", targetCard, (event) => {
    dragDropHandler(event);
  });
  // const deleteBtn = card.querySelector(".delete-btn.cursor-pointer");
  // deleteBtn.addEventListener("click", callbackRemoveEvt);
  // // card.addEvnetListener("dblclick", callbackChangeEvt);
  // const parent = util.$(`.task-column`);
  // parent.appendChild(card);
}

function renderRegisteredStyle(targetCard) {
  targetCard.classList.add("registered");
  targetCard.removeAttribute("id");
  util.$(".delete-btn.cursor-pointer", targetCard).classList.remove("hidden");
  util
    .$(".task-card__cancle-btn.cursor-pointer", targetCard)
    .classList.add("hidden");
  util
    .$(".task-card__register-btn.cursor-pointer", targetCard)
    .classList.add("hidden");
  util.$(".task-card__footer", targetCard).classList.remove("hidden");
  console.log(util.$(".task-card__title", targetCard));
  util.$(".task-card__title", targetCard).contentEditable = false;
  util.$(".task-card__content", targetCard).contentEditable = false;
}

export { renderCard };
