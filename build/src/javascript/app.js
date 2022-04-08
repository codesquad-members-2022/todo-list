import { renderRegister } from "./createRegister.js";
import { handleClickRegisterBtn } from "./updateRegisterCard.js";

const $registerBtn = document.querySelector(".register-button");

$registerBtn.addEventListener("click", handleClickRegisterBtn);
renderRegister();
