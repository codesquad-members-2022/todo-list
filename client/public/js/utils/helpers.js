export function qs(selector, scope = document) {
  return scope.querySelector(selector);
} // querySelector

export function qsAll(selector, scope = document) {
  return Array.from(scope.querySelectorAll(selector));
} // querySelectorAll and convert Array for easy to control

export function insertElement(target, option, element) {
  target.insertAdjacentHTML(option, element);
}
