import { axiosRequest } from "./util/util.js";

export function handleClickRegisterBtn(event) {
  const $selectedCard = event.target.closest(".card");
  const $cardDetails = $selectedCard.querySelector(".card-details");
  if (isLengthExceeded($cardDetails)) {
    alert("글자 수가 초과했습니다 (500자)");
    return;
  }
  const $cardTitle = $selectedCard.querySelector(".card-title");
  const cardColumnName = $selectedCard.closest(".column").id;
  const cardTitleText = $cardTitle.innerText;
  const cardDetailsText = $cardDetails.innerText;
  const cardRegisterTime = Date.now();
  const card = {
    column: cardColumnName,
    title: cardTitleText,
    detail: cardDetailsText,
    time: cardRegisterTime,
  };
  applyCardStyle($selectedCard);
  axiosRequest("post", "todos", card);
  preventModification($cardTitle, $cardDetails);
}

function isLengthExceeded($cardDetails) {
  const detailsLength = $cardDetails.innerText.length;
  const maxLength = 500;
  return detailsLength > maxLength;
}

function applyCardStyle($card) {
  const $buttonWrpper = $card.querySelector(".card-buttons-wrapper");
  Object.assign($card.style, {
    opacity: 1,
    border: "none",
  });
  Object.assign($buttonWrpper.style, {
    display: "none",
  });
}

function preventModification($cardTitle, $cardDetails) {
  $cardTitle.setAttribute("contenteditable", "false");
  $cardDetails.setAttribute("contenteditable", "false");
}
