import { getDate, sendMethodResult } from "../common/utils";
import Column from "../models/Column";

export const getColumns = sendMethodResult(async () => {
  const columns = await Column.find();
  return columns;
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
