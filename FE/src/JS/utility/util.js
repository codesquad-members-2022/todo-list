export const $ = (selected) => document.body.querySelector(selected);

export const $$ = (selected) => document.body.querySelectorAll(selected);

export const throttle = (callback, wait) => {
  let waiting;
  return (event) => {
    if (waiting) return;
    waiting = setTimeout(() => {
      callback(event);
      waiting = null;
    }, wait);
  };
};
