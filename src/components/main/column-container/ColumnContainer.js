import { Store } from "../../../stores/ColumnStore.js";
import { Column } from "./Column/Column.js";

export const renderColumnContainer = (parentEl) => {
  const columnContainerNode = getColumnContainerNode();
  parentEl.appendChild(columnContainerNode);
  mountColumn();
};

const getColumnContainerNode = () => {
  const columnContainerNode = document.createElement("div");
  columnContainerNode.className = "column-container";
  return columnContainerNode;
};

const mountColumn = () => {
  const columnOrder = Store.state.columnOrder;
  columnOrder.forEach((columnID) => new Column(columnID));
};
