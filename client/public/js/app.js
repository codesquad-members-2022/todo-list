import CSS from "../stylesheet/style.scss";

import Store from "./model/Store.js";
import usersData from "./tempStorage.js";

document.addEventListener("DOMContentLoaded", main);

function main() {
  const userStorage = getUserData("mansaout");

  const store = new Store(userStorage);


}

function getUserData(userId) {
  return storage[userId];
}
