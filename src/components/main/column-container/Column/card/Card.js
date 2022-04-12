import "./Card.scss";
import { Store } from "../../../../../stores/ColumnStore.js";
import { activateAlert } from "./alert/Alert.js";

export const initCard = (parentNode, cardState) => {
  const cardNode = makeCardNode(cardState);
  appendCardNode(cardNode, parentNode);
  renderCard(cardNode, cardState);
  setEvents(cardNode, cardState);
};

const makeCardNode = (cardState) => {
  const cardNode = document.createElement("div");
  cardNode.classList.add("card");
  if (cardState.type === "deleting") {
    cardNode.classList.add("card--deleting");
  }
  cardNode.dataset.id = cardState._id;
  return cardNode;
};

const appendCardNode = (cardNode, parentNode) => {
  parentNode.append(cardNode);
};

const renderCard = (cardNode, cardState) => {
  cardNode.innerHTML = makeCardInnerTemplate(cardState);
};

const makeCardInnerTemplate = (cardState) => {
  switch (cardState.type) {
    case "normal":
    case "deleting":
      return getNormalContentTemplate(cardState) + getNormalBtnTemplate();
    case "adding":
    case "editing":
      return getTempContentTemplate(cardState) + getConfirmBtnTemplate(cardState.type);
  }
};

const getNormalContentTemplate = (cardState) => {
  return `      
  <div class="card-contents">
    <div class="card-contents__title">${cardState.title}</div>
    <div class="card-contents__description">${cardState.description}</div>
    <div class="card-contents__author">${cardState.author}</div>
  </div>`;
};

const getTempContentTemplate = (cardState) => {
  const titleInput = `<input placeholder="제목을 입력하세요" value='${cardState.title || ""}' />`;
  const descriptionInput = `<input placeholder="내용을입력하세요" value='${cardState.description || ""}' />`;
  return `
    <div class="card-contents">
      <div class="card-contents__title">${titleInput}</div>
      <div class="card-contents__description">${descriptionInput}</div>
    </div>`;
};

const getNormalBtnTemplate = () => {
  return `<div class="card__delete-btn"></div>`;
};

const getConfirmBtnTemplate = (cardType) => {
  return `
  <div class="card__btns">
    <div class="card__btn card__cancel-btn">취소</div>
    <div class="card__btn card__confirm-btn" data-type='${cardType}'>${
    cardType === "adding" ? "등록" : "수정"
  }</div>
  </div>`;
};

const setEvents = (cardNode, cardState) => {
  switch (cardState.type) {
    case "normal":
      setDeleteBtnEvent(cardNode);
      setDoubleClickEvent(cardNode);
      break;
    case "adding":
    case "editing":
      setCancelBtnEvent(cardNode, cardState.type);
      setConfirmBtnEvent(cardNode);
      setInputEvent(cardNode);
  }
};

const setDeleteBtnEvent = (cardNode) => {
  const deleteBtn = cardNode.querySelector(".card__delete-btn");
  deleteBtn.addEventListener("click", () => handleDeleteBtnClickEvent(cardNode));
  deleteBtn.addEventListener("mouseenter", () => handleDeleteBtnMouseEvent(cardNode));
  deleteBtn.addEventListener("mouseleave", () => handleDeleteBtnMouseEvent(cardNode));
};

const setDoubleClickEvent = (cardNode) => {
  cardNode.addEventListener("dblclick", () => handleDoubleClickEvent(cardNode));
};

const setCancelBtnEvent = (cardNode, cardType) => {
  const cancelBtn = cardNode.querySelector(".card__cancel-btn");
  cancelBtn.addEventListener("click", () => handleCancelBtnEvent(cardNode, cardType));
};

const setConfirmBtnEvent = (cardNode) => {
  const confirmBtn = cardNode.querySelector(".card__confirm-btn");
  confirmBtn.addEventListener("click", () => handleConfirmBtnEvent(confirmBtn, cardNode));
};

const setInputEvent = (cardNode) => {
  cardNode.addEventListener("input", () => handleInputEvent(cardNode));
};

const handleDeleteBtnClickEvent = (cardNode) => {
  const [parentColumnID, cardID] = getIDs(cardNode);
  changeCardType(cardNode, "deleting");
  activateAlert(parentColumnID, cardID);
};

const handleDeleteBtnMouseEvent = (cardNode) => {
  cardNode.classList.toggle("card--deleting");
};

const handleDoubleClickEvent = (cardNode) => {
  changeCardType(cardNode, "editing");
};

const handleCancelBtnEvent = (cardNode, cardType) => {
  if (cardType === "adding") {
    deleteAddingForm(cardNode);
  } else if (cardType === "editing") {
    changeCardType(cardNode, "normal");
  }
};

const handleConfirmBtnEvent = (confirmBtn, cardNode) => {
  if (!confirmBtn.classList.contains("activated")) return;
  if (confirmBtn.dataset.type === "adding") {
    registerCardState(cardNode);
  } else if (confirmBtn.dataset.type === "editing") {
    changeCardState(cardNode);
  }
};

const handleInputEvent = (cardNode) => {
  const titleInput = cardNode.querySelector(".card-contents__title input");
  const descriptionInput = cardNode.querySelector(".card-contents__description input");
  const confirmBtn = cardNode.querySelector(".card__confirm-btn");
  onOffConfirmBtn(titleInput, descriptionInput, confirmBtn);
};

const isInputAllValid = (titleInput, descriptionInput) => {
  const titleInputLength = titleInput.value.trim().length;
  const descriptionInputLength = descriptionInput.value.trim().length;
  return Boolean(titleInputLength && descriptionInputLength);
};

const onOffConfirmBtn = (titleInput, descriptionInput, confirmBtn) => {
  if (isInputAllValid(titleInput, descriptionInput)) {
    confirmBtn.classList.add("activated");
  } else {
    confirmBtn.classList.remove("activated");
  }
};

const registerCardState = (cardNode) => {
  const [parentColumnID, cardID] = getIDs(cardNode);
  const newStateForPost = {
    title: cardNode.querySelector(".card-contents__title input").value,
    description: cardNode.querySelector(".card-contents__description input").value
  };
  Store.addCardState(parentColumnID, newStateForPost);
};

const changeCardState = (cardNode) => {
  const [parentColumnID, cardID] = getIDs(cardNode);
  const newStateForPost = {
    title: cardNode.querySelector(".card-contents__title input").value,
    description: cardNode.querySelector(".card-contents__description input").value
  };
  Store.changeCardState(parentColumnID, cardID, newStateForPost);
};

const changeCardType = (cardNode, type) => {
  const [parentColumnID, cardID] = getIDs(cardNode);
  Store.changeCardType(parentColumnID, cardID, type);
};

const deleteAddingForm = (cardNode) => {
  const [parentColumnID, cardID] = getIDs(cardNode);
  Store.unsetAddingCardState(parentColumnID);
};

const getIDs = (cardNode) => {
  const parentColumnID = cardNode.closest(".column").dataset.id;
  const cardID = cardNode.dataset.id;
  return [parentColumnID, cardID];
};

export const reRenderCard = (cardState) => {
  const cardNode = findCardNode(cardState._id);
  renderCard(cardNode, cardState);
  setEvents(cardNode, cardState);
};

const findCardNode = (cardID) => {
  return document.querySelector(`[data-id="${cardID}"]`);
};
