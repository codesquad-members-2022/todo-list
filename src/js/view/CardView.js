import * as util from "../../util/Util.js";

const renderCard = (callbackRemoveEvt, callbackChangeEvt) => {
  const deleteBtn = card.querySelector(".delete-btn.cursor-pointer");
  deleteBtn.addEventListener("click", callbackRemoveEvt);
  // card.addEvnetListener("dblclick", callbackChangeEvt);
  const parent = util.$(`.task-column`);
  parent.appendChild(card);
};

export { renderCard };
