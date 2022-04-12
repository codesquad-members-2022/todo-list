// import axios from "https://cdn.skypack.dev/axios";

export async function getData(URL) {
  const response = await axios.get(URL);
  const data = response.data;
  return data;
}

export async function postData(URL, updatedData) {
  await axios.post(URL, updatedData);
}

export async function putData(URL, updatedData) {
  await axios.put(URL, updatedData);
}

export async function deleteData(URL) {
  await axios.delete(URL);
}

export function axiosRequest(method, pathName, controlledData = {}) {
  const URL = `http://localhost:5000/${pathName}`;
  const idURL = URL + `/${controlledData.id}`;
  let response;
  switch (method) {
    case "get":
      const sortingURL = URL + "?_sort=";
      response = getData(URL);
      return response;
    case "post":
      postData(URL, controlledData);
      break;
    case "put":
      putData(idURL, controlledData);
      break;
    case "delete":
      deleteData(idURL);
      break;
  }
}

export function removeText({ target }) {
  target.innerText = "";
}
