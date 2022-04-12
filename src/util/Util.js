const $ = (query) => document.querySelector(query);

const $All = (query) => document.querySelectorAll(query);

const fetchData = async (url) => {
  try {
    const data = await fetch(url);
    return data.json();
  } catch (err) {
    console.err(err);
  }
};

function addEvent(targetNode, createCardFunc) {
  targetNode.addEventListener("click", createCardFunc);
}
export { $, $All, fetchData, addEvent };
