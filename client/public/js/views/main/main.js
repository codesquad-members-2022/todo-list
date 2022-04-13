import { btnPlusColumn } from "../../utils/assetURL";

export function createMain() {
  return `
    <main class="main">
      <ul class="column-list"></ul>
      <img class="column-add" src=${btnPlusColumn}>
    </main>
  `;
}
