import * as util from "../../util/Util.js";

function renderEmptyCard(
  parent,
  { inputHandler, registerBtnHandler, cancleBtnHandler },
  store
) {
  // 입력 값 확인 후 버튼 활성화 이벤트
  const newCard = getEmptyCardNode();
  util.on("keydown", newCard, inputHandler);

  // 등록 버튼 클릭 핸들링 이벤트
  const registerBtn = util.$(
    ".task-card__register-btn.cursor-pointer",
    newCard
  );
  util.on("click", registerBtn, (event) => {
    registerBtnHandler(event, store);
  });

  // 취소 버튼 클릭 핸들링 이벤트
  const removeBtn = util.$(".task-card__cancle-btn.cursor-pointer", newCard);
  util.on("click", removeBtn, (event) => {
    cancleBtnHandler(event, store);
  });

  const existCard = util.$(".task-card", parent);
  parent.insertBefore(newCard, existCard);
}

function getEmptyCardNode() {
  const newCard = document.createElement("div");
  const newCardHTML = createEmptyCardTemplate();
  newCard.classList.add("task-card");
  newCard.id = "empty-card";
  newCard.insertAdjacentHTML("afterbegin", newCardHTML);
  return newCard;
}

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

function getRegisteredCardNode(data) {
  const newCard = document.createElement("div");
  const newCardHTML = createRegisteredCardTemplate(data);
  newCard.classList.add("task-card", "registered");
  newCard.id = data.id;
  newCard.insertAdjacentHTML("afterbegin", newCardHTML);
  return newCard;
}

function createRegisteredCardTemplate(data) {
  return `
    <div class="task-card__header">
      <div
        class="task-card__title font-black"
        contenteditable="false"
        placeholder="제목을 입력하세요"
      >${data.title}</div>
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
      placeholder="내용을 입력하세요"
    >${data.content}</div>
    <div class="task-card__btns">
      <button class="task-card__cancle-btn cursor-pointer hidden">취소</button>
      <button class="task-card__register-btn cursor-pointer hidden">
        등록
      </button>
    </div>
    <div class="task-card__footer">
      author by <span id="author">web</span>
  </div>`;
}

export { renderEmptyCard, getRegisteredCardNode };
