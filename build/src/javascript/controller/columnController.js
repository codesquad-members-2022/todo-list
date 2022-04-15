import { getColumns } from "../model/columnModel.js";
import { addDeleteEvent } from "../view/cardView.js";
import { renderColumn } from "../view/columnView.js";

export async function renderTodos() {
  const columns = await getColumns();
  const columnNames = Object.keys(columns);
  for (const columnName of columnNames) {
    renderColumn(columnName, columns[columnName]);
  }
  addDeleteEvent();
}
