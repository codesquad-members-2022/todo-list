const getMockData = async () => {
  const res = await fetch("http://localhost:3000/data");
  const data = await res.json();
  return data[0];
};

const getColumnTitles = async () => {
  const mockData = await getMockData();
  const columns = mockData.columns;
  return columns.map((column) => ({
    title: column.title,
  }));
};

const getCards = async (columnIndex) => {
  const mockData = await getMockData();
  const columns = mockData.columns;
  const cards = columns[columnIndex].cards;

  return cards;
};

export { getColumnTitles, getCards };
