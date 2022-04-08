import { qs } from "../../utils/helpers.js";
import View from "../View.js";

export default class AsideView extends View {
  constructor() {
    super(qs("#aside"));
  }
}

// Header의 asideButton 클릭 시 나타나는 UI. 활동기록을 포함
qs(".header--history-btn").addEventListener("click", (e) => {
  qs(".history__bar").classList.add("show");
});

qs(".history--close-btn").addEventListener("click", (e) => {
  qs(".history__bar").classList.remove("show");
});
