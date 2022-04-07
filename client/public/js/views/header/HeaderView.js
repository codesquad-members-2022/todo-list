import { qs } from "../../utils/helpers.js";
import View from "../View.js";

export default class HeaderView extends View {
  constructor() {
    super(qs("#header"));

    this.asideButton = qs("#aside-button", this.element);
  }
}

// 현재 Header 구성요소 : 타이틀(투두리스트), aside 버튼
