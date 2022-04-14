const getMockData = async (resource) => {
  const res = await fetch("http://localhost:3000/" + resource);
  const data = await res.json();
  return data;
};

const postData = async (resource, data) => {
  fetch("http://localhost:3000/" + resource, {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    body: JSON.stringify(data),
  });
};

const getColumns = async () => {
  const columns = await getMockData("columns");
  return columns.map(({ title, id }) => ({
    title: title,
    id: id,
  }));
};

const getCards = async (columnIndex) => {
  const cards = await getMockData(`cards?columnId=${columnIndex}`);
  cards.sort((a, b) => b.id - a.id);
  return cards;
};

const postCard = async (card) => {
  postData("cards", card);
};

const getHistories = async () => {
  const histories = await getMockData("histories");
  return histories;
};

export { getColumns, getCards, postCard, getHistories };
