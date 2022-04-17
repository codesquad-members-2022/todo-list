const port = 3000;

function $(query, baseNode = document) {
  return baseNode.querySelector(query);
}

function $All(query, baseNode = document) {
  return baseNode.querySelectorAll(query);
}

function on(eventType, targetNode, handlerFn) {
  targetNode.addEventListener(eventType, handlerFn);
}

async function fetchData(url) {
  try {
    const data = await fetch(url);
    return data.json();
  } catch (err) {
    console.err(err);
  }
}

async function postData(url, data) {
  const response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  //return response.json();
}

async function updateData(url, data) {
  const response = await fetch(url, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  //return response.json();
}

async function deleteData(url) {
  const response = await fetch(url, {
    method: "DELETE",
  });
  //return response.json();
}

export { port, $, $All, on, fetchData, postData, updateData, deleteData };
