import {
  postTodo,
  putUpdatedCardData,
  deleteServerCardData,
} from "../model/cardModel.js";
import { getUpdatedCardContent } from "../view/cardView.js";

export function addServerCardData(card) {
  const cardData = getUpdatedCardContent(card);
  postTodo(cardData);
}

export function updateServerCardData($selectedCard) {
  const cardData = getUpdatedCardContent($selectedCard);
  const dataID = $selectedCard.id.slice(4);
  putUpdatedCardData(cardData, dataID);
}

export function deleteCardData($selectedCard) {
  const dataId = $selectedCard.id.slice(4);
  deleteServerCardData(dataId);
}
