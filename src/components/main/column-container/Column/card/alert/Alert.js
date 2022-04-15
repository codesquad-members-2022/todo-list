import "./alert.scss";
import { CARD_TYPE } from "@/common/variable.js";
import { Store } from "@/stores/ColumnStore.js";

export const activateAlert = (columnID, cardID) => {
  const alertDOM = document.querySelector(".alert-container");
  showAlert(alertDOM);
  setEvents(alertDOM, columnID, cardID);
};

const showAlert = (alertDOM) => {
  alertDOM.classList.remove("hidden");
};

const setEvents = (alertDOM, columnID, cardID) => {
  setCancelBtnEvent(alertDOM, columnID, cardID);
  setDeletBtnEvent(alertDOM, columnID, cardID);
};

const setCancelBtnEvent = (alertDOM, columnID, cardID) => {
  const cancelBtn = document.querySelector(".alert__util-btn--cancel");
  cancelBtn.addEventListener("click", () => handleCancelBtnClickEvent(alertDOM, columnID, cardID));
};

const setDeletBtnEvent = (alertDOM, columnID, cardID) => {
  const deleteBtn = document.querySelector(".alert__util-btn--delete");
  deleteBtn.addEventListener("click", () => handleDeleteBtnClickEvent(alertDOM, columnID, cardID));
};

const handleDeleteBtnClickEvent = (alertDOM, columnID, cardID) => {
  hideAlert(alertDOM);
  Store.deleteCard(columnID, cardID);
};

const handleCancelBtnClickEvent = (alertDOM, columnID, cardID) => {
  hideAlert(alertDOM);
  Store.changeCardType(columnID, cardID, CARD_TYPE.NORMAL);
};

const hideAlert = (alertDOM) => {
  alertDOM.classList.add("hidden");
};
