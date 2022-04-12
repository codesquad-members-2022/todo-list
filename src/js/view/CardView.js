import * as UTIL from "../util/Util.js";

const createCard = (data) => {
  const newCard = document.createElement("div");
  const newCardHTML = getCardTemplate(data);
  newCard.classList.add("task-card registered");
  newCard.innerHTML = newCardHTML;
  return newCard;
};

const getCardTemplate = (data) => {
  return /*html*/ `          
  <div class="task-card__header">
    <div
      class="task-card__title font-black"
      contenteditable="false"
    >
      GitHub 공부하기
    </div>
    <span class="delete-btn cursor-pointer"
      ><svg
        width="16"
        height="16"
        viewBox="0 0 12 12"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z"
          fill="gray"
        />
      </svg>
    </span>
  </div>
  <div
    class="task-card__content font-black"
    contenteditable="false"
  >
    add, commit, push
  </div>
  <div class="task-card__btns">
    <button class="task-card__cancle-btn hidden">취소</button>
    <button class="task-card__register-btn hidden">등록</button>
  </div>
  <div class="task-card__footer">
    author by <span id="author">web</span>
  </div>
`;
};

const renderCard = (data, callbackRemoveEvt, callbackChangeEvt) => {
  const card = createCard(data);
  const deleteBtn = card.querySelector(".delete-btn.cursor-pointer");
  deleteBtn.addEventListener("click", callbackRemoveEvt);
  card.addEvnetListener("dblclick", callbackChangeEvt);
  const parent = UTIL.$(`${data.column}`);
  parent.appendChild(card);
};

export { renderCard };
