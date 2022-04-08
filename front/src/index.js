import App from "./App";
import peact from "./core/peact";

peact.setRoot(document.querySelector(".App"), App);
peact.render();
