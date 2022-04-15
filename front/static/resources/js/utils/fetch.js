const HTTP_REQUEST = {
  PUT(data) {
    return {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    };
  },
  DELETE() {
    return {
      method: 'DELETE'
    };
  },
  POST(data) {
    return {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    };
  }
};

const fetchRequest = async (url, option = { method: 'GET' }) => {
  const response = await fetch(url, option);
  if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
  return response.json();
};

export { HTTP_REQUEST, fetchRequest };
