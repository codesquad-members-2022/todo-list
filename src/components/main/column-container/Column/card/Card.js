import "./Card.scss";
import { Store } from "../../../../../stores/ColumnStore.js";

export const renderCard = (parentEl, columnID, cardID) => {
  const cardData = Store.state[columnID].cards[cardID];
  const cardType = cardData.type;
  parentEl.insertAdjacentHTML("beforeend", getCardTemplate(cardType, cardData));
};

const getCardTemplate = (cardType, cardData) => {
  switch (cardType) {
    case "normal":
      return getNormalTemplate(cardData);
    case "editing":
      return getEditingTemplate(cardData);
    case "new":
      return getNewTemplate();
  }
};

const getNormalTemplate = (cardData) => {
  return `
    <li class="card card--normal">
        <div class="card-contents">
            <div class="card-contents__title">${cardData.title}</div>
            <div class="card-contents__description">${cardData.description}</div>
            <div class="card-contents__author">${cardData.author}</div>
        </div>
        <div class="card__delete-btn"></div>
    </li>
  `;
};

function getEditingTemplate(cardData) {
  return `
    <li class="card card--editing">
        <div class="card-contents">
            <div class="card-contents__title"><input value=${cardData.title} /></div>
            <div class="card-contents__description"><input value=${cardData.description} /></div>
        </div>
        <div class="card__util-btns">
            <div class="card__util-btn card__util-btn--cancel">취소</div>
            <div class="card__util-btn card__util-btn--regist">수정</div>
        </div>
    </li>
  `;
}

function getNewTemplate() {
  return `
    <li class="card card--editing">
        <div class="card-contents">
            <div class="card-contents__title"><input placeholder="제목을 입력하세요" /></div>
            <div class="card-contents__description"><input placeholder="내용을 입력하세요" /></div>
        </div>
        <div class="card__util-btns">
            <div class="card__util-btn card__util-btn--cancel">취소</div>
            <div class="card__util-btn card__util-btn--regist">등록</div>
        </div>
    </li>`;
}
