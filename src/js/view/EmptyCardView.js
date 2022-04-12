const getEmptyCardNode = () => {
  const newCard = document.createElement("div");
  const newCardHTML = createEmptyCardTemplate();
  newCard.classList.add("task-card");
  newCard.innerHTML = newCardHTML;
  return newCard;
};

function createEmptyCardTemplate() {
  return `
    <div class="task-card__header">
      <div
        class="task-card__title"
        contenteditable="true"
        placeholder="제목을 입력하세요"
      ></div>
      <span class="delete-btn cursor-pointer hidden"
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
      class="task-card__content"
      contenteditable="true"
      placeholder="내용을 입력하세요"
    ></div>
    <div class="task-card__btns">
      <button class="task-card__cancle-btn cursor-pointer">취소</button>
      <button class="task-card__register-btn cursor-pointer" disabled>
        등록
      </button>
    </div>
    <div class="task-card__footer hidden">
      author by <span id="author">web</span>
  </div>`;
}

const renderEmptyCard = (parent, registerData, checkInput, store) => {
  const newCard = getEmptyCardNode();
  newCard
    .querySelector(".task-card__register-btn.cursor-pointer")
    .addEventListener("click", (event) => {
      registerData(event, store);
    });

  newCard.addEventListener("keydown", (event) => {
    checkInput(event);
  });
  parent.appendChild(newCard);
};

export { renderEmptyCard };
