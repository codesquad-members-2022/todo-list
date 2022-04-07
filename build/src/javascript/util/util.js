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

export async function deleteData(URL) {
  await axios.delete(URL);
}

export async function axiosRequest(request, dataName, data = {}) {
  const URL = `http://localhost:5000/${dataName}`;
  const idURL = URL + `/${data.id}`;
  let response;
  switch (request) {
    case "get":
      response = await getData(URL);
      return response;
    case "post":
      await postData(URL, data);
      break;
    case "put":
      await putData(idURL, data);
      break;
    case "delete":
      await deleteData(idURL);
      break;
  }
}

//putData

//deleteData
