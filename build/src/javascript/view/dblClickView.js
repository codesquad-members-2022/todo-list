import { updateServerCardData } from "../controller/dblClickController.js";
import {
  applyRegisterBoxStyle,
  applyCardStyle,
  updateCard,
  setTextAreaContenteditable,
} from "./cardView.js";

export function addCardDoubleClickEvent($cards) {
  $cards.addEventListener("dblclick", handleDoubleClickEvent);
}

function handleDoubleClickEvent({ target }) {
  const $clickedCard = target.closest(".card");
  if ($clickedCard) {
    displayButtons($clickedCard);
    applyRegisterBoxStyle($clickedCard);
    setTextAreaContenteditable($clickedCard, true);
    changeIntoEditButton($clickedCard);
    addButtonClickEvent($clickedCard);
  }
}

function displayButtons($card) {
  const $buttonWrpper = $card.querySelector(".card-buttons-wrapper");
  $buttonWrpper.style.display = "flex";
}

function changeIntoEditButton($card) {
  const $registerBtn = $card.querySelector(".register-button");
  if (!$registerBtn) return;
  $registerBtn.removeEventListener("click", updateCard);
  $registerBtn.className = "edit-button";
  $registerBtn.innerText = "수정";
}

function addButtonClickEvent($card) {
  const $editBtn = $card.querySelector(".edit-button");
  const $cancelBtn = $card.querySelector(".cancel-button");
  $editBtn.addEventListener("click", handleEditButtonEvent);
  $cancelBtn.addEventListener("click", handleCancelButtonEvent);
}

function handleEditButtonEvent({ target }) {
  const $clickedCard = target.closest(".card");
  updateServerCardData($clickedCard);
  applyCardStyle($clickedCard);
  setTextAreaContenteditable($clickedCard, false);
}

function handleCancelButtonEvent({ target }) {
  const $clickedCard = target.closest(".card");
  applyCardStyle($clickedCard);
  setTextAreaContenteditable($clickedCard, false);
}
