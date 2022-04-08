import { handleClickRegisterBtn } from "./updateRegisterCard.js";
import { axiosRequest } from "./util/util.js";
export function renderRegister() {
  const $plusBtn = document.querySelector("#have-to-do-plus");
  $plusBtn.addEventListener("click", function () {
    handleRegister();
  });
}

async function handleRegister() {
  const $cards = document.querySelector("#have-to-do-cards");
  const todoCount = $cards.children.length;
  const registerBoxTemp = `
    <div class="card"  id="card${todoCount + 1}">
      <div class="card-contents-wrapper row-sort">
        <div class="card-text-area">
          <div
            class="card-title title-font"
            contentEditable="true"
          >
            제목을 입력하세요
          </div>
          <div class="card-details"  contentEditable="true">
            내용을 입력하세요
          </div>
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
    </div>
  `;
  $cards.insertAdjacentHTML("afterbegin", registerBoxTemp);
  const cardId = `#card${todoCount + 1}`;
  const $card = document.querySelector(cardId);
  const $registerTitle = $card.querySelector(".card-title");
  const $registerDetail = $card.querySelector(".card-details");
  const $cancelBtn = $card.querySelector(".cancel-button");
  const $registerBtn = $card.querySelector(".register-button");
  changeRegisterBoxStyle($cards);
  $registerDetail.addEventListener("click", ({ target }) => {
    removeText(target);
  });
  $registerTitle.addEventListener("click", ({ target }) => {
    removeText(target);
  });
  $cancelBtn.addEventListener("click", () => {});
  $registerBtn.addEventListener("click", ({ target }) => {
    handleClickRegisterBtn(target);
  });
}
function changeRegisterBoxStyle($cards) {
  const $registerCard = $cards.firstElementChild;
  Object.assign($registerCard.style, {
    border: "1px solid var(--blue)",
    opacity: 0.5,
  });
}
// util로 빼기!
function removeText($) {
  $.innerText = "";
}
