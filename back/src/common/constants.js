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

const BASE_URL = {
  production: "https://bbpark-todolist.herokuapp.com:",
  development: "http://localhost:",
};

export const SERVICE_DOMAIN = "https://healtheloper.github.io";

export const API_URL = (PORT) => `${BASE_URL[process.env.NODE_ENV]}${PORT}/`;
