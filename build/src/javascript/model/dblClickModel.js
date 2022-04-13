import { axiosRequest } from "../util/util.js";

export function putUpdatedCardData(cardData, dataID) {
  axiosRequest("put", "todos", cardData, dataID);
}
