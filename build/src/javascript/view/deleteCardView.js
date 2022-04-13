import { removeCard } from "./cardView.js";
import { deleteCardData } from "../controller/deleteCardController.js";

export function handleAlertEvent() {
  const $cancelBtn = document.querySelector("#alert-cancel-btn");

  const $crossBtns = document.querySelectorAll(".card-cross-button");
  for (const $crossBtn of $crossBtns) {
    $crossBtn.addEventListener("click", showAlert);
  }
  $cancelBtn.addEventListener("click", blockAlert);
}

function blockAlert() {
  const alert = document.querySelector("#alert-id");
  alert.style.display = "none";
}

export function showAlert({ target }) {
  const alert = document.querySelector("#alert-id");
  alert.style.display = "flex";
  const $selectedCard = target.closest(".card");
  const $editBtn = document.querySelector("#alert-edit-btn");
  $editBtn.addEventListener("click", function () {
    deleteCardData($selectedCard);
    removeCard({ target });
    blockAlert();
  });
  $editBtn.removeEventListener("click", handleAlertEvent);
}
