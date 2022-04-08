export const addEventAfterRender = ({ eventType, selector, callback }) => {
  setTimeout(() => {
    const $element = document.getElementById(selector);
    $element.addEventListener(eventType, callback);
  }, 0);
};
