import { btnHamburger } from "../../utils/assetURL";

export function createHeader() {
  return `
    <header class="header">
      <h1>TO-DO LIST</h1>
      <img class="header--history-btn" src=${btnHamburger}></img>
    </header>
  `;
}
