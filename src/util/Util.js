const $ = (query, baseNode = document) => baseNode.querySelector(query);

const $All = (query, baseNode = document) => baseNode.querySelectorAll(query);

const fetchData = async (url) => {
  try {
    const data = await fetch(url);
    return data.json();
  } catch (err) {
    console.err(err);
  }
};

function on(eventType, targetNode, handlerFn) {
  targetNode.addEventListener(eventType, handlerFn);
}

export { $, $All, fetchData, on };
