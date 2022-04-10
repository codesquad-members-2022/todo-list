import "./app.scss";
import { renderHeader } from "./components/header/Header.js";
import { renderMain } from "./components/main/Main.js";
import "./components/header/menu/Menu.js";
import "./components/main/fab/Fab.js";
import "./components/main/column-container/Column/Column.js";
import "./components/main/column-container/Column/card/Card.js";
import "./components/main/column-container/Column/card/alert/Alert.js";

function runApp() {
  renderApp();
}

function renderApp() {
  renderHeader(document.body);
  renderMain(document.body);
}

runApp();

