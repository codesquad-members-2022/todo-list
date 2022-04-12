import { createColumnController } from "./js/Controller/MainController.js";
import { createStore } from "./js/model/Store.js";

(async () => {
  const store = createStore([]);
  createColumnController(store);
})();
