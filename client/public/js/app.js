import Store from "./model/Store.js";
import storage from "./tempStorage.js";

import HeaderView from "./views/HeaderView.js";
import AsideView from "./views/AsideView.js";
import ColumnsView from "./views/ColumnsView.js";

document.addEventListener("DOMContentLoaded", main);

function main() {
  const userStorage = getUserData("mansaout");

  const store = new Store(userStorage);

  const views = {
    headerView: new HeaderView(),
    asideView: new AsideView(),
    columnsView: new ColumnsView(),
  };

  new Controller(store, views);
}

function getUserData(userId) {
  return storage[userId];
}