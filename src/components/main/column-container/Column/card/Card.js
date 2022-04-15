import "./Card.scss";
import { insertNodeBefore, insertNodeAfter, removeNodeself, hasClassName, getNodeIndex } from "@/common/util";
import { CARD_TYPE } from "@/common/variable";
import { Store } from "@/stores/ColumnStore";
import { activateAlert } from "./alert/Alert";

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
  const { title, description, author } = cardState;
  return `      
  <div class="card-contents">
    <div class="card-contents__title">${title}</div>
    <div class="card-contents__description">${description}</div>
    <div class="card-contents__author">${author}</div>
  </div>`;
};

const getTempContentTemplate = (cardState) => {
  const { title, description } = cardState;
  const titleInput = `<input placeholder="제목을 입력하세요" value='${title || ""}' />`;
  const descriptionInput = `<input placeholder="내용을 입력하세요" value='${description || ""}' />`;
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
  deleteBtn.addEventListener("mouseup", (e) => handleDeleteBtnClickEvent(e, cardNode));
  deleteBtn.addEventListener("mouseenter", () => handleDeleteBtnMouseEvent(cardNode));
  deleteBtn.addEventListener("mouseleave", () => handleDeleteBtnMouseEvent(cardNode));
};

const setMouseDownEvent = (cardNode) => {
  cardNode.addEventListener("mousedown", (e) => handleMouseDownEvent(e, cardNode));
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

const handleDeleteBtnClickEvent = (e, cardNode) => {
  const [parentColumnID, cardID] = getIDs(cardNode);
  changeCardType(cardNode, CARD_TYPE.DELETING);
  activateAlert(parentColumnID, cardID);
  e.stopPropagation();
};

const handleDeleteBtnMouseEvent = (cardNode) => {
  cardNode.classList.toggle("card--deleting");
};

const handleMouseDownEvent = (e, cardNode) => {
  if (e.detail === 1) {
    handleSingleMouseDownEvent(e, cardNode);
  }
  if (e.detail >= 2) {
    handleDoubleMouseDownEvent(cardNode);
  }
};

const handleDoubleMouseDownEvent = (cardNode) => {
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
  if (!hasClassName(confirmBtn, "activated")) return;
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

const handleSingleMouseDownEvent = (e, cardNode) => {
  const [originalCardTop, originalCardLeft] = getOriginalCardPositions(cardNode);
  const originalParentColumnID = getIDs(cardNode)[0];
  const originalCardIdx = getNodeIndex(cardNode);
  const shadowCardNode = makeShadowCardNode(cardNode);
  const followingCardNode = makeFollowingCardNode(cardNode, originalCardTop, originalCardLeft);

  const shiftX = e.clientX - originalCardLeft;
  const shiftY = e.clientY - originalCardTop;

  const mouseMoveHandler = getMouseMoveHandler(shiftX, shiftY, shadowCardNode, followingCardNode);
  const mouseUpHandler = getMouseUpHandler(
    mouseMoveHandler,
    shadowCardNode,
    followingCardNode,
    originalParentColumnID,
    originalCardIdx
  );
  document.body.addEventListener("mousemove", mouseMoveHandler);
  document.body.addEventListener("mouseup", mouseUpHandler, { once: true });
};

const getOriginalCardPositions = (cardNode) => {
  const originalCardTop = cardNode.getBoundingClientRect().top;
  const originalCardLeft = cardNode.getBoundingClientRect().left;
  return [originalCardTop, originalCardLeft];
};

const makeShadowCardNode = (cardNode) => {
  const shadowCardNode = cardNode.cloneNode(true);
  shadowCardNode.classList.add("shadow");
  insertNodeBefore(shadowCardNode, cardNode);
  return shadowCardNode;
};

const makeFollowingCardNode = (cardNode, originalCardTop, originalCardLeft) => {
  const followingCardNode = cardNode;
  followingCardNode.classList.add("following");
  moveFollowingCard(followingCardNode, originalCardLeft, originalCardTop);
  return followingCardNode;
};

const moveFollowingCard = (followingCardNode, posX, posY) => {
  followingCardNode.style.left = posX + "px";
  followingCardNode.style.top = posY + "px";
};

const getMouseMoveHandler = (shiftX, shiftY, shadowCardNode, followingCardNode) => (event) => {
  handleFollowingCard(shiftX, shiftY, followingCardNode, event);
  handleCardShadow(shadowCardNode, followingCardNode, event);
};

const getMouseUpHandler =
  (mouseMoveHandler, shadowCardNode, followingCardNode, originalParentColumnID, originalCardIdx) => () => {
    endDragDropEvent(
      mouseMoveHandler,
      shadowCardNode,
      followingCardNode,
      originalParentColumnID,
      originalCardIdx
    );
  };

const handleFollowingCard = (shiftX, shiftY, followingCardNode, event) => {
  const [posX, posY] = getCardPosition(shiftX, shiftY, event);
  moveFollowingCard(followingCardNode, posX, posY);
};

const getCardPosition = (shiftX, shiftY, event) => {
  const x = event.pageX - shiftX;
  const y = event.pageY - shiftY;
  return [x, y];
};

const handleCardShadow = (shadowCardNode, followingCardNode, event) => {
  const underMouseElement = getUnderMouseElement(followingCardNode, event.clientX, event.clientY);
  const underMouseElementClass = inspectElementClass(underMouseElement);
  moveCardShadow(underMouseElementClass, underMouseElement, shadowCardNode, event);
};

const moveCardShadow = (underMouseElementClass, underMouseElement, shadowCardNode, event) => {
  switch (underMouseElementClass) {
    case "card":
      moveToAroundCard(underMouseElement, shadowCardNode, event);
      return;
    case "card-content":
      const parentCardElement = underMouseElement.closest(".card");
      moveToAroundCard(parentCardElement, shadowCardNode, event);
      return;
    case "column":
      moveToColumn(underMouseElement, shadowCardNode);
      return;
  }
};

const moveToAroundCard = (underMouseCardElement, cardNode, event) => {
  const rect = underMouseCardElement.getBoundingClientRect();
  if (event.clientY < (rect.top + rect.bottom) / 2) {
    insertNodeBefore(cardNode, underMouseCardElement);
  } else {
    insertNodeAfter(cardNode, underMouseCardElement);
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

const endDragDropEvent = (
  mouseMoveHandler,
  shadowCardNode,
  followingCardNode,
  originalParentColumnID,
  originalCardIdx
) => {
  document.body.removeEventListener("mousemove", mouseMoveHandler);
  putFollowingCardOnShadow(followingCardNode, shadowCardNode);
  disconnectFollowingCard(followingCardNode);
  removeNodeself(shadowCardNode);
  updateResult(followingCardNode, originalParentColumnID, originalCardIdx);
};

const putFollowingCardOnShadow = (followingCardNode, shadowCardNode) => {
  insertNodeBefore(followingCardNode, shadowCardNode);
};

const disconnectFollowingCard = (followingCardNode) => {
  followingCardNode.classList.remove("following");
  followingCardNode.style.left = 0;
  followingCardNode.style.top = 0;
};

const updateResult = (followingCardNode, originalParentColumnID, originalCardIdx) => {
  const [newParentColumnID, cardID] = getIDs(followingCardNode);
  const movedIdx = getNodeIndex(followingCardNode);
  if (isCardPositionChanged(originalParentColumnID, newParentColumnID, originalCardIdx, movedIdx))
    Store.changeCardPosition(originalParentColumnID, cardID, newParentColumnID, movedIdx);
};

const isCardPositionChanged = (originalParentColumnID, newParentColumnID, originalCardIdx, movedIdx) => {
  return originalParentColumnID !== newParentColumnID || originalCardIdx !== movedIdx;
};

export const reRenderCard = (cardState) => {
  const cardNode = findCardNode(cardState._id);
  renderCard(cardNode, cardState);
  setEvents(cardNode, cardState);
};

const findCardNode = (cardID) => {
  return document.querySelector(`[data-id="${cardID}"]`);
};
