export const $ = (selector, target = document) =>
  target.querySelector(selector);
export const $$ = (selector, target = document) =>
  target.querySelectorAll(selector);

export const closest = (selector, target) => target.closest(selector);

export const containClass = (target, className) =>
  target.classList.contains(className);
