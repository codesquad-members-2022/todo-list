export const LOG_TYPE = {
  MOVE: "move",
  UPDATE: "update",
  CREATE: "create",
  DELETE: "delete",
};

export const TABLE_NAME = {
  TODO: "todo",
  TODO_LOGS: "todoLogs",
  COLUMN: "column",
  LAST_ID: "lastId",
};

export const API_URL = (PORT) => `http://localhost:${PORT}/`;
