import { Store } from "../../../stores/ColumnStore.js";
import { Column } from "./Column/Column.js";
import { pipe } from "../../../util/util.js";

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
  const columnOrder = Store.state.columnOrder;
  columnOrder.forEach((columnID) => new Column(columnID));
  //columnOrder.forEach((columnID) => initColumn(columnContainerNode, columnState[columnID]));
};
