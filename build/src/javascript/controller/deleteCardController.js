import { deleteServerCardData } from "../model/deleteCardModel.js";

export function deleteCardData($selectedCard) {
  const dataId = $selectedCard.id.slice(4);
  deleteServerCardData(dataId);
}
