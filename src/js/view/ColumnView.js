function getColumnTemplate() {
  return /*html*/ `
  <div class="task-column__header__title">
    <span class="column__title">해야할 일</span
    ><span class="column__card-cnt">0</span>
  </div>
  <div class="task-column__header__btn">
    <span class="add-btn cursor-pointer"
      ><svg
        width="18"
        height="18"
        viewBox="0 0 14 14"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="M0.105709 7.53033L0.105709 6.46967H6.46967V0.105713H7.53033V6.46967H13.8943V7.53033H7.53033V13.8943H6.46967V7.53033H0.105709Z"
          fill="gray"
        />
      </svg> </span
    ><span class="delete-btn cursor-pointer"
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
  `;
}

function getColumnNode() {
  const column = document.createElement("div");
  const columnHTML = getColumnTemplate();
  column.classList.add("task-column__header");
  column.innerHTML = columnHTML;
  return column;
}

function initColumn(parent) {
  parent.appendChild(getColumnNode());
}

function onClickColumnAddBtn(parent, callbackEvent, store) {
  const addCardBtn = parent.querySelector(".add-btn.cursor-pointer");
  addCardBtn.addEventListener("click", (event) => {
    callbackEvent(event, store);
  });
}

export { initColumn, onClickColumnAddBtn };
