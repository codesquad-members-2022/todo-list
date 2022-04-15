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

export const getURL = (router) => `http://localhost:3002/${router}`;

export const requestToServer = async (url, method = 'GET', data) => {
  switch (method) {
    case 'POST':
      fetch(url, {
        method: 'POST',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify(data),
      });
      break;
    case 'PATCH':
      fetch(url, {
        method: 'PATCH',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify(data),
      });
      break;
    case 'Delete':
      fetch(url, {
        method: 'DELETE',
        headers: { 'content-type': 'application/json' },
      });
      break;
  }
};

export const getSequenceData = async (columnName) => {
  const res = await fetch(getURL('cardSequence'));
  const json = await res.json();
  const sequence = json[columnName];

  return sequence;
};
