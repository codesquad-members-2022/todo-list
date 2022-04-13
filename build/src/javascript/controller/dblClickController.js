import { putUpdatedCardData } from "../model/dblClickModel.js";
import { getUpdatedCardContent } from "../view/cardView.js";

export function updateServerCardData($selectedCard) {
  const cardData = getUpdatedCardContent($selectedCard);
  const dataID = $selectedCard.id.slice(4, 5);
  putUpdatedCardData(cardData, dataID);
}
