import { axiosRequest } from "../util/util.js";

export function deleteServerCardData(dataId) {
  axiosRequest("delete", "todos", {}, dataId);
}
