export const $ = (selector, target = document) =>
  target.querySelector(selector);

export const $$ = (selector, target = document) =>
  target.querySelectorAll(selector);

export const closest = (selector, target) => target.closest(selector);

export const containClass = (target, className) =>
  target.classList.contains(className);

export const pipe =
  (...func) =>
  (input) =>
    func.reduce((chain, func) => chain.then(func), Promise.resolve(input));

export const debounce = (callback, delay) => {
  let timerId;
  return (event) => {
    if (timerId) clearTimeout(timerId);
    timerId = setTimeout(callback, delay, event);
  };
};
