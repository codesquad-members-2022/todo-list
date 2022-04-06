import axios from "https://cdn.skypack.dev/axios";

export async function getData(dataName) {
  const URL = `http://localhost:5000/${dataName}`;
  const response = await axios.get(URL).then((response) => response.data);
  const data = await response;
  return response;
}
