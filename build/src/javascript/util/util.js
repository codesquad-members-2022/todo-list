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

export function axiosRequest(method, pathName, controlledData = {}, dataId) {
  const URL = `http://localhost:5000/${pathName}`;
  const idURL = URL + `/${dataId}`;
  console.log(idURL);
  switch (method) {
    case "get":
      const sortingURL = URL + "?_sort=createDate&_order=desc";
      const response = getData(sortingURL);
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
