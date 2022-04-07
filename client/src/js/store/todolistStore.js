import { fetchData } from "../utils/utils.js";

export const getTodolistData = () => {
  return fetchData("http://localhost:3000/todolist");
};
