import "./app.scss";
import { columnStore } from "./stores/ColumnStore.js";
import { initHeader, renderHeader } from "./components/header/Header.js";
import { initMain } from "./components/main/Main.js";

(() => {
  //data fetch 받아와서 Store initial set해주고 함수 실행
  //여기서부터 데이터 넘겨주기 시작? 아니면 데이터가 필요해지는 columnContainer.js 부터?
  initHeader();
  initMain();
})();
