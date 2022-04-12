const createEmptyCard = () => {
  const newCard = document.createElement("div");
  newCard.classList.add("testNewCard");
  const input = document.createElement("input");
  const input2 = document.createElement("input");
  const button = document.createElement("button");
  newCard.appendChild(input);
  newCard.appendChild(input2);
  newCard.appendChild(button);
  newCard.querySelector("button").classList.add("testButton");
  return newCard;
  // initCard(newCard);
};

function createEmptyCardTemplate() {
  return `<div class="task-card">
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
    </div>
  </div>`;
}

const renderEmptyCard = (parent, callbackEvent, store) => {
  const newCard = createEmptyCard();
  newCard.qeurySelector(".testButton").addEventListener("click", (event) => {
    callbackEvent(event, store);
  });
  parent.appendChild(newCard);
};
