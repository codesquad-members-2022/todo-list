import * as util from "../../util/Util.js";

function onClickColumnAddBtn(columnNodes, handlerFn, store) {
  columnNodes.forEach((columnNode) => {
    const addCardBtn = util.$(".add-btn.cursor-pointer", columnNode);
    util.on("click", addCardBtn, (event) => {
      handlerFn(event, store);
    });
  });
}

export { onClickColumnAddBtn };
