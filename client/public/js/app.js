import CSS from "../stylesheet/style.scss";

import Store from "./model/Store.js";
import usersData from "./tempStorage.js";

import { subscribeEvents } from "./handler/handler.js";
import { renderer } from "./views/renderer.js";

document.addEventListener("DOMContentLoaded", app);

function app() {
  const userData = getUserData("mansaout");

  const store = new Store(userData);

  renderer.allColumns(store.columns);
  renderer.allItems(store.items);
  renderer.allHistory(store.history);
  
  // test itembox
  renderer.itemBox(1);
  
  subscribeEvents();
}

function getUserData(userId) {
  const [userData] = usersData.filter((data) => data.id === userId);
  if (!userData) throw "no user";

  return userData;
}
