const FETCH_OPTION = {
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
  try {
    const response = await fetch(url, option);
    return response.json();
  } catch (error) {
    throw new Error(error);
  }
};

export { FETCH_OPTION, fetchRequest };
