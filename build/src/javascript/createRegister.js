import { axiosRequest } from "./util/util.js";
export function renderRegister() {
  const $plusBtn = document.querySelector("#have-to-do-plus");
  $plusBtn.addEventListener("click", function () {
    handleRegister();
  });
}

function handleRegister() {
  const $cards = document.querySelector("#have-to-do-cards");
  const $registerBox = `
    <div class="card">
      <div class="card-contents-wrapper row-sort">
        <div class="card-text-area">
          <div class="card-title title-font" contentEditable = "true">제목을 입력하세요</div>
          <div class="card-details" contentEditable = "true">내용을 입력하세요
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
  $cards.insertAdjacentHTML("afterbegin", $registerBox);
}
