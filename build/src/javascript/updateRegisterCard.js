import { axiosRequest } from "./util/util.js";

export async function handleClickRegisterBtn(event) {
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
  preventModification($cardTitle, $cardDetails);
  axiosRequest("post", "todos", card);
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

export async function renderingTodos() {
  const todos = await axiosRequest("get", "todos");
  const haveToDo = todos.filter((todo) => todo.column === "have-to-do-column");
  const doingTodo = todos.filter((todo) => todo.column === "doing-column");
  const doneTodo = todos.filter((todo) => todo.column === "done-column");
  renderingColumn("#have-to-do-column", haveToDo);
  renderingColumn("#doing-column", doingTodo);
  renderingColumn("#done-column", doneTodo);
  attachEventListener();
}

function renderingColumn(columnId, todos) {
  const $column = document.querySelector(columnId);
  const $cards = $column.querySelector(".cards");
  const cardTemplate = todos.reduce((template, todo) => {
    template += `<div class="card">
    <div class="card-contents-wrapper row-sort">
      <div class="card-text-area">
        <div class="card-title title-font" contenteditable="true">${todo.title}</div>
        <div class="card-details" contenteditable="true">${todo.detail}</div>
      </div>
      <figure class="card-cross-button">
        <img
          src="./image/crossBtn.svg"
          alt="cross-button-img"
          class="cross-button-img"
        />
      </figure>
    </div>
    <div class="card-buttons-wrapper">
      <button class="cancel-button center-sort">취소</button>
      <button class="register-button center-sort">등록</button>
    </div>
  </div>`;
    return template;
  }, "");
  $cards.insertAdjacentHTML("afterbegin", cardTemplate);
}

function attachEventListener() {
  const $registerBtns = document.querySelectorAll(".register-button");
  for (let $registerBtn of $registerBtns) {
    $registerBtn.addEventListener("click", handleClickRegisterBtn);
  }
}
