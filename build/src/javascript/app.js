import { init } from "./view/cardView.js";
import { renderTodos } from "./controller/cardController.js";

window.addEventListener("DOMContentLoaded", renderTodos);
init();
