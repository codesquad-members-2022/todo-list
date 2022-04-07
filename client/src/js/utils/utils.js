export const $ = (selector) => document.querySelector(selector);
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

export const fetchData = async (url) => {
  const response = await fetch(url);

  if (response.status === 200) {
    const data = await response.json();
    return data;
  } else {
    throw new Error("Fetch Failed");
  }
};
