import { btnPlusColumn } from "../../utils/assetURL";

export function createMain() {
  return `
    <main class="main">
      <ul class="column-list"></ul>
    </main>
    <img class="column--add-btn" src=${btnPlusColumn}>
  `;
}
