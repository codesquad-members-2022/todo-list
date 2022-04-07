import "./Card.scss";

function renderNormalCard(cardID) {}

function handleDoubleClick() {}

export default function getTemplate(type, cardData = none) {
  switch (type) {
    case "normal":
      return getNormalTemplate(cardData);
    case "new":
      return getNewTemplate();
    case "editing":
      return getEditingTemplate(cardData);
  }
}

function getNormalTemplate(cardData) {
  return `
    <li class="card" data-id=${cardData.id}>
        <div class="card-contents">
            <div class="card-contents__title">${cardData.title}</div>
            <div class="card-contents__description">${cardData.description}</div>
            <div class="card-contents__author">${cardData.author}</div>
        </div>
        <div class="card__delete-btn"></div>
    </li>`;
}

function getNewTemplate() {
  const cardID = makeCardID();
  return `
    <li class="card card--editing" data-id=${cardID}>
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

function getEditingTemplate(cardData) {
  return `
    <li class="card card--editing" data-id=${cardData.id}>
        <div class="card-contents">
            <div class="card-contents__title"><input value=${cardData.title} /></div>
            <div class="card-contents__description"><input value=${cardData.description} /></div>
        </div>
        <div class="card__util-btns">
            <div class="card__util-btn card__util-btn--cancel">취소</div>
            <div class="card__util-btn card__util-btn--regist">수정</div>
        </div>
    </li>`;
}

function makeCardID() {
  return new Date().getUTCMilliseconds();
}
