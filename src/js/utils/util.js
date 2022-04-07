export const fetchData = async url => {
  try {
    const data = await fetch(url);
    return data.json();
  } catch (err) {
    console.log(err);
  }
};

export const appendElementsToBody = (...elements) => {
  elements.forEach(element => document.body.appendChild(element));
};
