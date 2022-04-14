import { getDate, sendMethodResult } from "../common/utils";
import Column from "../models/Column";

export const getColumns = sendMethodResult(async () => {
  const columns = await Column.find();
  return columns;
});

export const getColumnById = sendMethodResult(async (req) => {
  const {
    params: { id },
  } = req;

  const column = await Column.findById(id);

  if (!column) {
    throw Error("해당하는 ID의 Column 이 없습니다.");
  }

  return column;
});

export const deleteColumnById = sendMethodResult(async (req) => {
  const {
    params: { id },
  } = req;
  const deletedColumn = await Column.findByIdAndRemove(id);
  return deletedColumn;
});

export const postColumnCreate = sendMethodResult(async (req) => {
  const {
    body: { title },
  } = req;
  const createdAt = getDate();
  const updatedAt = createdAt;
  const newColumn = await Column.create({
    title,
    createdAt,
    updatedAt,
  });
  return newColumn;
});
