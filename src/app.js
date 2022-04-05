import "./app.scss";
import Header from "./components/header.js";
import "./components/card.js";
import "./components/column.js";
import "./components/menu.js";
import "./components/fab.js";
import "./components/alert.js";

function runApp() {
  renderApp();
}

function renderApp() {
  const header = new Header();
  header.render();
}

runApp();

