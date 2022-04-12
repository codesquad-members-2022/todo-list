import "./style/style.scss";
import { createMainController } from "./js/Controller/MainController.js";
import { createStore } from "./js/model/Store.js";
import * as UTIL from "./util/Util.js";

(async () => {
  // const savedData = await fetchData(url);
  const store = createStore();
  createMainController(store);
})();
