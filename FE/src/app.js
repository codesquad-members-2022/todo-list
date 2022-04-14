import "../src/styles/main.scss";
import Columns from "./components/Columns.js";
import Histories from "./components/Histories.js";

window.addEventListener("DOMContentLoaded", () => {
  const worksEl = document.querySelector(".works");
  const historyEl = document.querySelector(".history");
  const columns = new Columns(worksEl);
  columns.render();
  const histories = new Histories(historyEl);
  histories.addEvent();
});
