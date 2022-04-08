import { renderRegister } from "./createRegister.js";
import {
  handleClickRegisterBtn,
  renderingTodos,
} from "./updateRegisterCard.js";
const $registerBtn = document.querySelector(".register-button");

$registerBtn.addEventListener("click", handleClickRegisterBtn);
renderRegister();

window.addEventListener("DOMContentLoaded", renderingTodos);
