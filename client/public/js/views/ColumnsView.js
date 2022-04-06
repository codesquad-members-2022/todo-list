import { qs } from "../utils/helpers.js";
import View from "./View.js";

export default class ColumnsView extends View {
  constructor() {
    super(qs("#columns"));
  }
}

class Template {
  getColumnList(data = []) {}

  getColumn() {}

  getItemList(data = []) {}

  getItem({ item_title, content }) {}
}

// 컬럼 리스트를 포함, 개별 컬럼안에는 아이템리스트를 포함
