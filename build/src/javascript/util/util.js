import axios from "https://cdn.skypack.dev/axios";

export async function getData(URL) {
  const data = await axios.get(URL).then((response) => response.data);
  return data;
}

export async function postData(URL, updatedData) {
  const data = await axios
    .post(URL, updatedData)
    .then((response) => response.data);
  return data;
}

export async function putData(URL, updatedData) {
  const data = await axios
    .put(URL, updatedData)
    .then((response) => response.data);
  return data;
}

export async function axiosRequest(request, dataName, data = {}) {
  const URL = `http://localhost:5000/${dataName}`;
  const idURL = URL + `/${data.id}`;
  let response;
  switch (request) {
    case "get":
      response = await getData(URL);
      break;
    case "post":
      response = await postData(URL, data);
      break;
    case "put":
      response = await putData(idURL, data);
      break;
    case "delete":
      break;
  }
  return response;
}

//putData

//deleteData
