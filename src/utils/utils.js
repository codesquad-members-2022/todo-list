export const $ = (selector, base = document) => {
  return base.querySelector(selector);
};

export const $$ = (selector, base = document) => {
  return [...base.querySelectorAll(selector)];
};

export const on = ({ target, eventName, handler }) => {
  target.addEventListener(eventName, handler);
};

export const delegate = ({ target, eventName, selector, handler }) => {
  const emitEvent = event => {
    const potentialElements = $$(selector, target);

    for (const potentialElement of potentialElements) {
      if (potentialElement === event.target) {
        return handler.call(event.target, event);
      }
    }
  };
  console.log(target);
  on({ target, eventName, handler: emitEvent });
};

export const emit = (target, eventName, detail) => {
  const event = new CustomEvent(eventName, { detail });
  target.dispatchEvent(event);
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
