import { renderTodos } from "./controller/columnController.js";
import { addPlusBtnEvent, handleMouseMoveEvent } from "./view/columnView.js";

init();

function init() {
  window.addEventListener("DOMContentLoaded", renderTodos);
  addDragEvent();
  addPlusBtnEvent();
}

function addDragEvent() {
  document.body.addEventListener("mousemove", handleMouseMoveEvent);
}
