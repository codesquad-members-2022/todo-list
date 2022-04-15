import "../stylesheet/style.scss";

import Store from "./model/Store.js";

import {
  renderHeader,
  renderMain,
  renderAside,
  renderAllColumns,
  renderAllItems,
  renderAllHistory,
} from "./views/renderer.js";

import { bindEvents } from "./handler/eventHandler.js";

import { getUserData } from "./utils/helpers.js";

document.addEventListener("DOMContentLoaded", app);

async function app() {
  const userData = await getUserData("mansaout");

  const store = new Store(userData);

  // initial HTML structure
  renderHeader();
  renderMain();
  renderAside();

  // render initial user data
  renderAllColumns(store.getColumns());
  renderAllItems(store.getItems());
  renderAllHistory(store.getHistory());

  // bind events
  bindEvents(store);
}
