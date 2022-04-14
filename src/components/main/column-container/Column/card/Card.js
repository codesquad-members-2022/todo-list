import "./Card.scss";
import { CARD_TYPE } from "../../../../../common/variable.js";
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
  if (cardState.type === CARD_TYPE.DELETING) {
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
    case CARD_TYPE.NORMAL:
    case CARD_TYPE.DELETING:
      return getNormalContentTemplate(cardState) + getNormalBtnTemplate();
    case CARD_TYPE.ADDING:
    case CARD_TYPE.EDITING:
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
    cardType === CARD_TYPE.ADDING ? "등록" : "수정"
  }</div>
  </div>`;
};

const setEvents = (cardNode, cardState) => {
  switch (cardState.type) {
    case CARD_TYPE.NORMAL:
      setDeleteBtnEvent(cardNode);
      setDoubleClickEvent(cardNode);
      setMouseDownEvent(cardNode);
      break;
    case CARD_TYPE.ADDING:
    case CARD_TYPE.EDITING:
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
  changeCardType(cardNode, CARD_TYPE.DELETING);
  activateAlert(parentColumnID, cardID);
};

const handleDeleteBtnMouseEvent = (cardNode) => {
  cardNode.classList.toggle("card--deleting");
};

const handleDoubleClickEvent = (cardNode) => {
  changeCardType(cardNode, CARD_TYPE.EDITING);
};

const handleCancelBtnEvent = (cardNode, cardType) => {
  if (cardType === CARD_TYPE.ADDING) {
    deleteAddingForm(cardNode);
  } else if (cardType === CARD_TYPE.EDITING) {
    changeCardType(cardNode, CARD_TYPE.NORMAL);
  }
};

const handleConfirmBtnEvent = (confirmBtn, cardNode) => {
  if (!confirmBtn.classList.contains("activated")) return;
  if (confirmBtn.dataset.type === CARD_TYPE.ADDING) {
    registerCardState(cardNode);
  } else if (confirmBtn.dataset.type === CARD_TYPE.EDITING) {
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

//drag n drop
function classifyByEventCnt(singleEventHandler, interval) {
  let cnt = 0;
  let timer;

  return (...args) => {
    cnt++;
    timer = setTimeout(() => {
      if (cnt === 1) {
        singleEventHandler(...args);
        cnt = 0;
      }
    }, interval);
    if (cnt === 2) {
      clearTimeout(timer);
      // doubleEventHandler(...args);
      cnt = 0;
    }
  };
}

const setMouseDownEvent = (cardNode) => {
  const singleMouseDownEventHandler = getSingleMouseDownEventHandler(cardNode);
  //const doubleMouseDownEventHandler = getDoubleMouseDownEventHandler(cardNode);
  cardNode.addEventListener("mousedown", classifyByEventCnt(singleMouseDownEventHandler, 200));
};

const getSingleMouseDownEventHandler = (cardNode) => (e) => {
  handleSingleMouseDownEvent(cardNode, e);
};

const getDoubleMouseDownEventHandler = (cardNode) => () => {
  handleDoubleClickEvent(cardNode);
};

const handleSingleMouseDownEvent = (cardNode, e) => {
  //readyForMove
  // const shadowCardNode = cardNode.cloneNode(true);
  const followingCardNode = cardNode.cloneNode(true);
  followingCardNode.classList.add("following");
  document.body.append(followingCardNode);
  followingCardNode.style.left = cardNode.getBoundingClientRect().left + "px";
  followingCardNode.style.top = cardNode.getBoundingClientRect().top - 15 + "px";
  const shiftX = e.clientX - cardNode.getBoundingClientRect().left;
  const shiftY = e.clientY - cardNode.getBoundingClientRect().top;

  cardNode.classList.add("shadow");

  const handleMouseMove = getMouseMoveHandler(shiftX, shiftY, cardNode, followingCardNode);
  const handleMouseUp = getMouseUpHandler(handleMouseMove, cardNode, followingCardNode);
  document.body.addEventListener("mousemove", handleMouseMove);
  document.body.addEventListener("mouseup", handleMouseUp, { once: true });
};

const getMouseMoveHandler = (shiftX, shiftY, cardNode, followingCardNode) => (event) => {
  handleFollowingCard(shiftX, shiftY, followingCardNode, event);
  handleCardShadow(cardNode, followingCardNode, event);
};

const getMouseUpHandler = (mouseMoveHandler, cardNode, followingCardNode) => () => {
  endDragDropEvent(mouseMoveHandler, cardNode, followingCardNode);
};

const handleFollowingCard = (shiftX, shiftY, followingCardNode, event) => {
  const [x, y] = getCardPosition(shiftX, shiftY, event);
  moveFollowingCard(followingCardNode, x, y);
};

const getCardPosition = (shiftX, shiftY, event) => {
  const x = event.pageX - shiftX + "px";
  const y = event.pageY - shiftY + "px";
  return [x, y];
};

const moveFollowingCard = (followingCardNode, x, y) => {
  followingCardNode.style.left = x;
  followingCardNode.style.top = y;
};

const handleCardShadow = (cardNode, followingCardNode, event) => {
  const underMouseElement = getUnderMouseElement(followingCardNode, event.clientX, event.clientY);
  const underMouseElementClass = inspectElementClass(underMouseElement);
  moveCardShadow(underMouseElementClass, underMouseElement, cardNode, event);
};

const moveCardShadow = (underMouseElementClass, underMouseElement, cardNode, event) => {
  switch (underMouseElementClass) {
    case "card":
      moveToAroundCard(underMouseElement, cardNode, event);
      return;
    case "card-content":
      const parentCardElement = underMouseElement.closest(".card");
      moveToAroundCard(parentCardElement, cardNode, event);
      return;
    case "column":
      moveToColumn(underMouseElement, cardNode);
      return;
  }
};

const moveToAroundCard = (underMouseCardElement, cardNode, event) => {
  const rect = underMouseCardElement.getBoundingClientRect();
  if (event.clientY < (rect.top + rect.bottom) / 2) {
    underMouseCardElement.parentNode.insertBefore(cardNode, underMouseCardElement);
  } else {
    underMouseCardElement.parentNode.insertBefore(cardNode, underMouseCardElement.nextElementSibling);
  }
};

const moveToColumn = (underMouseColumnElement, cardNode) => {
  const cardList = underMouseColumnElement.querySelector(".card-list");
  cardList.append(cardNode);
};

const getUnderMouseElement = (followingCardNode, x, y) => {
  followingCardNode.hidden = true;
  const underMouseElement = document.elementFromPoint(x, y);
  followingCardNode.hidden = false;
  return underMouseElement;
};

const inspectElementClass = (underMouseElement) => {
  if (hasClassName(underMouseElement, "card") && !hasClassName(underMouseElement, "shadow")) {
    return "card";
  } else if (hasClassName(underMouseElement, "column")) {
    return "column";
  } else if (underMouseElement.closest(".card")) {
    return "card-content";
  } else {
    return "others";
  }
};

const hasClassName = (element, className) => {
  return element.classList.contains(className);
};

const endDragDropEvent = (mouseMoveHandler, cardNode, followingCardNode) => {
  document.body.removeEventListener("mousemove", mouseMoveHandler);
  document.body.removeChild(followingCardNode);
  cardNode.classList.remove("shadow");
  //서버한테 전송하는 등의 마무리 작업 로직 추가 필요
};
