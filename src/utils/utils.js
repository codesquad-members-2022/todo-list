export const $ = (selector, base = document) => {
  return base.querySelector(selector);
};

export const $$ = (selector, base = document) => {
  return base.querySelectorAll(selector);
};

export const addClass = (className, element) => {
  if (!element) return;
  element.classList.add(className);
};

export const removeClass = (className, element) => {
  if (!element) return;
  element.classList.remove(className);
};

export const toggleClass = (className, element) => {
  if (!element) return;
  element.classList.toggle(className);
};

export const createElement = (tagName, className, attrs = {}) => {
  const element = document.createElement(tagName);
  if (className) {
    if (Array.isArray(className)) element.className = className.join(' ');
    else element.className = className;
  }
  Object.entries(attrs).forEach(([key, value]) => {
    element.setAttribute(key, value);
  });
  return element;
};

export const delay = ms => {
  return new Promise(resolve => {
    setTimeout(() => resolve(), ms);
  });
};

export const debounce = (cb, delay) => {
  let timerId;
  return event => {
    if (timerId) {
      clearTimeout(timerId);
    }
    timerId = setTimeout(cb, delay, event);
  };
};

export const throttle = (cb, delay) => {
  let timerId;
  return event => {
    if (timerId) return;
    timerId = setTimeout(() => {
      cb(event);
      timerId = null;
    }, delay);
  };
};
