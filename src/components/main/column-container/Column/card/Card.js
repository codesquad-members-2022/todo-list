import "./Card.scss";
import { Store } from "../../../../../stores/ColumnStore.js";

export class Card {
  constructor(parentColumnID, cardID) {
    this.parentColumnID = parentColumnID;
    this.cardID = cardID;
    this.setNode();
    this.renderContent();
    this.activate();
  }

  activate() {
    Store.subscribe("card", () => this.renderContent());
  }

  setNode() {
    const parentColumnDOM = document.querySelector(`[data-columnid="${this.parentColumnID}"]`);
    const cardNode = this.makeCardNode();
    parentColumnDOM.append(cardNode);
    this.cardNode = cardNode;
  }

  makeCardNode() {
    const cardNode = document.createElement("div");
    cardNode.className = "card";
    cardNode.dataset.cardID = this.cardID;
    return cardNode;
  }

  renderContent() {
    const cardData = Store.state[this.parentColumnID].cards[this.cardID];
    const cardType = cardData.type;
    this.cardNode.innerHTML = this.getInnerTemplate(cardType, cardData);
    this.cacheDOM(cardType);
    this.setEvents(cardType);
  }

  getInnerTemplate(cardType, cardData) {
    const contentTemplate =
      cardType === "normal" ? this.getNormalContentTemplate(cardData) : this.getTempContentTemplate(cardData);
    const btnTemplate =
      cardType === "normal" ? this.getNormalBtnTemplate() : this.getTempBtnTemplate(cardType);
    return contentTemplate + btnTemplate;
  }

  getNormalContentTemplate(cardData) {
    return `      
    <div class="card-contents">
      <div class="card-contents__title">${cardData.title}</div>
      <div class="card-contents__description">${cardData.description}</div>
      <div class="card-contents__author">${cardData.author}</div>
    </div>`;
  }

  getTempContentTemplate(cardData) {
    const titleInput = `<input placeholder="제목을 입력하세요" value='${cardData.title || ""}' />`;
    const descriptionInput = `<input placeholder="내용을입력하세요" value='${cardData.description || ""}' />`;

    return `
      <div class="card-contents">
        <div class="card-contents__title">${titleInput}</div>
        <div class="card-contents__description">${descriptionInput}</div>
      </div>`;
  }

  getNormalBtnTemplate() {
    return `<div class="card__delete-btn"></div>`;
  }

  getTempBtnTemplate(cardType) {
    return `
    <div class="card__btns">
      <div class="card__btn card__cancel-btn">취소</div>
      <div class="card__btn card__confirm-btn">${cardType === "new" ? "등록" : "수정"}</div>
    </div>`;
  }

  cacheDOM(cardType) {
    if (cardType === "normal") {
      this.deletBtn = this.cardNode.querySelector(".card__delete-btn");
    } else {
      this.cancelBtn = this.cardNode.querySelector(".card__cancel-btn");
      this.confirmBtn = this.cardNode.querySelector(".card__confirm-btn");
      this.titleInput = this.cardNode.querySelector(".card-contents__title input");
      this.descriptionInput = this.cardNode.querySelector(".card-contents__description input");
    }
  }

  setEvents(cardType) {
    switch (cardType) {
      case "normal":
        this.setDeleteBtnEvent();
        this.setDoubleClickEvent();
        break;
      case "new":
      case "editing":
        this.setCancelBtnEvent();
        this.setConfirmBtnEvent();
        this.setInputEvent();
    }
  }

  setDeleteBtnEvent() {
    this.deletBtn.addEventListener("click", () => this.handleDeleteBtnEvent());
  }

  setDoubleClickEvent() {
    this.cardNode.addEventListener("dblclick", () => this.handleDoubleClickEvent());
  }

  setCancelBtnEvent() {
    this.cancelBtn.addEventListener("click", () => this.handleCancelBtnEvent());
  }

  setConfirmBtnEvent() {
    this.confirmBtn.addEventListener("click", () => this.handleConfirmBtnEvent());
  }

  setInputEvent() {
    this.titleInput.addEventListener("input", (e) => this.handelInputEvent(e));
    this.descriptionInput.addEventListener("input", (e) => this.handelInputEvent(e));
  }

  handleDeleteBtnEvent() {
    //todo: alert창 뜬 후에 삭제하도록 기능 추가해야함
    this.deleteCard();
  }

  handleDoubleClickEvent() {
    this.switchToEditMode();
  }

  handleCancelBtnEvent() {
    this.deleteCard();
    //todo: edit 모드면 기존 카드가 나오게 해야함.
  }

  handleConfirmBtnEvent(e) {
    if (!this.confirmBtn.classList.contains("activated")) {
      return;
    }
    this.changeCardData();
  }

  handelInputEvent(e) {
    this.toggleConfrimBtn(e);
  }

  deleteCard() {
    Store.deleteCard(this.parentColumnID, this.cardID);
  }

  switchToEditMode() {
    Store.chageToEditState(this.parentColumnID, this.cardID);
  }

  changeCardData() {
    const cardData = {
      columnID: this.parentColumnID,
      type: "normal",
      title: this.titleInput.value,
      description: this.descriptionInput.value,
      author: "author by web",
    };
    Store.changeCard(this.parentColumnID, this.cardID, cardData);
  }

  toggleConfrimBtn(e) {
    if (e.target.value.trim().length === 0) {
      this.confirmBtn.classList.remove("activated");
    } else {
      this.confirmBtn.classList.add("activated");
    }
  }
}
