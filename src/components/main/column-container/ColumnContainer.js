import { Store } from "../../../stores/ColumnStore.js";
import { renderColumn } from "./Column/Column.js";

export const renderColumnContainer = (parentEl) => {
  const columnContainerNode = getColumnContainerNode();
  parentEl.appendChild(columnContainerNode);
  mountColumn(columnContainerNode);
};

const getColumnContainerNode = () => {
  const columnContainerNode = document.createElement("div");
  columnContainerNode.className = "column-container";
  return columnContainerNode;
};

const mountColumn = (parentEl) => {
  const columnOrder = Store.state.columnOrder;
  columnOrder.forEach((columnID) => renderColumn(parentEl, columnID));
};
