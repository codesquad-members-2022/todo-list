const getMockData = async (resource) => {
  const res = await fetch("http://localhost:3000/" + resource);
  const data = await res.json();
  return data;
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
  return cards;
};

const getHistories = async () => {
  const mockData = await getMockData("histories");
  return mockData.histories;
};

export { getColumns, getCards, getHistories };
