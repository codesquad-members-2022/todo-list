import * as util from "../../util/Util.js";

function onClickColumnAddBtn(columnNodes, addBtnHandler, store) {
  columnNodes.forEach((columnNode) => {
    const addCardBtn = util.$(".add-btn.cursor-pointer", columnNode);
    util.on("click", addCardBtn, (event) => {
      addBtnHandler(event, store);
    });
  });
}

export { onClickColumnAddBtn };
