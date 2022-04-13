import { addPlusBtnEvent } from "./view/cardView.js";
import { renderTodos } from "./controller/cardController.js";

init();

function init() {
  window.addEventListener("DOMContentLoaded", renderTodos);
  addPlusBtnEvent();
}
