export const $ = (selector) => document.querySelector(selector);
export const $$ = (selector) => document.querySelectorAll(selector);
export const debounce = (callback, delay) => {
  let timerId;
  return (event) => {
    if (timerId) clearTimeout(timerId);
    timerId = setTimeout(
      () => {
        callback(event);
      },
      delay,
      event
    );
  };
};

const handleError = async (response) => {
  if (response.status >= 200 && response.status < 300) {
    const data = await response.json();
    return data;
  } else {
    throw new Error("Fetch Failed");
  }
};

export const fetchData = async (url) => {
  const response = await fetch(url);
  return handleError(response);
};

export const putData = async (url, data) => {
  const response = await fetch(url, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return handleError(response);
};

export const postData = async (url, data) => {
  const response = await fetch(url, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return handleError(response);
};
