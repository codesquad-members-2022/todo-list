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
