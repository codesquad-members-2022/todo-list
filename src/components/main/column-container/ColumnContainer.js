import { Store } from "../../../stores/ColumnStore.js";
import { initColumn } from "./Column/Column.js";
import { pipe } from "../../../common/util.js";

export const initColumnContainer = (parentNode) => {
  pipe(renderColumnContainer, mountColumn)(parentNode);
};

const renderColumnContainer = (parentNode) => {
  const columnContainerNode = makeColumnContainerDOM();
  parentNode.append(columnContainerNode);
  return columnContainerNode;
};

const makeColumnContainerDOM = () => {
  const columnContainerNode = document.createElement("div");
  columnContainerNode.className = "column-container";
  return columnContainerNode;
};

const mountColumn = (columnContainerNode) => {
  const columnState = Store.state;
  const columnOrder = columnState.columnOrder;
  columnOrder.forEach((columnID) => initColumn(columnContainerNode, columnState[columnID]));
};
