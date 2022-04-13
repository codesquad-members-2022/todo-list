import { btnX } from "../../utils/assetURL";

export function createAside() {
  return `
  <aside class="history__bar">
  <img class="history--close-btn" src=${btnX} />
  <ul class="history-list"></ul>
</aside>
  `;
}
