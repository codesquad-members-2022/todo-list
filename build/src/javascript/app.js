import { renderTodos } from "./controller/columnController.js";
import { addPlusBtnEvent, addmouseMoveEvent } from "./view/columnView.js";

init();

function init() {
  window.addEventListener("DOMContentLoaded", renderTodos);
  addmouseMoveEvent();
  addPlusBtnEvent();
}
