const BASE_URL = 'http://localhost:3000';

const HTTP_METHOD = {
  POST(data) {
    return {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    };
  },
  PATCH(data) {
    return {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    };
  },
  DELETE() {
    return {
      method: 'DELETE',
    };
  },
};

const request = async (url, option = {}) => {
  const response = await fetch(url, option);
  if (!response.ok) {
    console.error();
  } else {
    return response.json();
  }
};

export { request, BASE_URL, HTTP_METHOD };
