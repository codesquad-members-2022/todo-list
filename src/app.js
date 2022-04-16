import "./app.scss";
import { Store, observer } from "@/stores/ColumnStore";
import { initHeader } from "@/components/header/Header";
import { initMain } from "@/components/main/Main";
import { reRenderColumn } from "@/components/main/column-container/Column/Column";
import { reRenderCard } from "@/components/main/column-container/Column/card/Card";

(async () => {
  await Store.setInitialState();
  observer.subscribe("column", reRenderColumn);
  observer.subscribe("card", reRenderCard);
  initHeader();
  initMain();
})();
