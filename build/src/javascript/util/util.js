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

export function axiosRequest(request, dataName, data = {}) {
  const URL = `http://localhost:5000/${dataName}`;
  const idURL = URL + `/${data.id}`;
  let response;
  switch (request) {
    case "get":
      response = getData(URL);
      return response;
    case "post":
      postData(URL, data);
      break;
    case "put":
      putData(idURL, data);
      break;
    case "delete":
      deleteData(idURL);
      break;
  }
}

export function removeText({ target }) {
  target.innerText = "";
}
