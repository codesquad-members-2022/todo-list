// webpack test entry 파일
import { axios } from "axios";

async function getData(dataName) {
  const URL = `http://localhost:5000/${dataName}`;
  const response = await axios.get(URL);
  return response;
}

const todoData = getData("todos");
console.log(todoData);
