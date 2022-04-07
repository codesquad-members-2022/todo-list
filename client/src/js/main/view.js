import { $ } from "../utils/utils.js";

const columnTaskComment = $(".column__task--comment");

const autosizeTextArea = () => {
  columnTaskComment.style.height = "1px";
  columnTaskComment.style.height = columnTaskComment.scrollHeight + "px";
};

const setEvents = () => {
  columnTaskComment.addEventListener("input", autosizeTextArea);
};

export const mainInit = (parent) => {
  setEvents();
};
