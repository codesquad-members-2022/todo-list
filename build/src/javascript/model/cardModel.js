import { axiosRequest } from "../API/api.js";

export function postTodo(cardData) {
  axiosRequest("post", "todos", cardData);
}

export function putUpdatedCardData(cardData, dataID) {
  axiosRequest("put", "todos", cardData, dataID);
}

export function deleteServerCardData(dataId) {
  axiosRequest("delete", "todos", {}, dataId);
}
