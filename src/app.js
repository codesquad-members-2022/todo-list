import "./app.scss";
import { Store, observer } from "./stores/ColumnStore.js";
import { initHeader } from "./components/header/Header.js";
import { initMain } from "./components/main/Main.js";
import { reRenderColumn } from "./components/main/column-container/Column/Column.js";
import { reRenderCard } from "./components/main/column-container/Column/card/Card.js";

(async () => {
  await Store.setInitialState();
  observer.subscribe("column", reRenderColumn);
  observer.subscribe("card", reRenderCard);
  initHeader();
  initMain();
})();
