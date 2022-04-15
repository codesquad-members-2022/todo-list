import * as util from "../../util/Util.js";

function getHistoryCard(column, title, type) {
  const historyCard = document.createElement("article");
  const historyCardHMTL = `
  <article class="history-card">
    <div class="history-card__left">🥳</div>
    <div class="history-card__right">
      <div id="user-name">@sam</div>
      <div class="history-card__content">
        ${column}에 ${checkTitleAndType(title, type)}
      </div>
      <div class="history-card__time">1 분전</div>
    </div>
  </article>`;
  historyCard.classList.add("history-card");
  historyCard.innerHTML = historyCardHMTL;
  return historyCard;
}

function checkTitleAndType(title, type) {
  if (type === "remove") {
    return `${title} 을 삭제하였습니다.`;
  }

  if (type === "register") {
    return `${title} 을 등록하였습니다.`;
  }

  if (type === "update") {
    return `${title} 을 수정하였습니다.`;
  }
}

function renderHistoryCard({ column, title, action }) {
  const menu = document.querySelector(".side-menu-bar__wrapper");
  const targetColumn = column;
  const targetTitle = title;
  const historyCard = getHistoryCard(targetColumn, targetTitle, action);
  const prevHistoryCard = util.$(".history-card", menu);

  menu.insertBefore(historyCard, prevHistoryCard);
}

function onClickMeunRemoveBtn() {
  const menu = util.$("#side-menu-bar");
  const deleteBtn = util.$(".delete-btn.cursor-pointer", menu);
  console.log(menu, deleteBtn);
  util.on("click", deleteBtn, () => {
    menu.classList.add("hide");
  });
}

export { renderHistoryCard, onClickMeunRemoveBtn };
