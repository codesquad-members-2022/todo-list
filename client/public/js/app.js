import "../stylesheet/style.scss";

import Store from "./model/Store.js";
import usersData from "./tempStorage.js";

import {
  renderHeader,
  renderMain,
  renderAside,
  renderAllColumns,
  renderAllItems,
  renderAllHistory,
  renderColumn,
} from "./views/renderer.js";

import { bindEvents } from "./handler/eventHandler.js";

document.addEventListener("DOMContentLoaded", app);

function app() {
  const userData = getUserData("mansaout");

  const store = new Store(userData);

  // initial HTML structure
  renderHeader();
  renderMain();
  renderAside();

  // render initial user data
  renderAllColumns(store.columns);
  renderAllItems(store.items);
  renderAllHistory(store.history);

  // bind events
  bindEvents(store);
}

function getUserData(userId) {
  const [userData] = usersData.filter((data) => data.id === userId);
  if (!userData) throw "no user";

  return userData;
}
