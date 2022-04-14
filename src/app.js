import "./style/style.scss";
import { createMainController } from "./js/Controller/MainController.js";
import { createStore } from "./js/model/Store.js";
import * as util from "./util/Util.js";

(async () => {
  const URL = {
    todo: "http://localhost:3000/todo",
    doing: "http://localhost:3000/doing",
    done: "http://localhost:3000/done",
    history: "http://localhost:3000/history",
  };

  //TODO: PromiseAll로 바꾸기
  const savedData = {};
  for (const key in URL) {
    const data = await util.fetchData(URL[key]);
    savedData[key] = data;
  }

  const store = createStore(savedData);

  createMainController(store);
})();
