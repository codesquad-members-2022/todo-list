import "./app.scss";
import Header from "./components/header/Header.js";
import "./components/header/menu/Menu.js";
import "./components/main/fab/Fab.js";
import "./components/main/column-container/Column/Column.js";
import "./components/main/column-container/Column/card/Card.js";
import "./components/main/column-container/Column/card/alert/Alert.js";

function runApp() {
  renderApp();
}

function renderApp() {
  const header = new Header();
  header.render();
}

runApp();

