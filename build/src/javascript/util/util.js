export function removeText({ target }) {
  if (
    target.classList.contains("card-title") ||
    target.classList.contains("card-details")
  ) {
    target.innerText = "";
  }
}

export function isTextLengthExceeded(text) {
  const textLength = text.length;
  const maxLength = 500;
  return textLength > maxLength;
}

export function hideElement($element) {
  $element.style.display = "none";
}
export function debounce(func, delay) {
  let timeId;
  return function (...args) {
    if (timeId) {
      clearTimeout(timeId);
    }
    timeId = setTimeout(() => {
      func(...args);
    }, delay);
  };
}
export function throttle(callback, limit = 100) {
  let waiting = false;
  return function (...args) {
    if (!waiting) {
      callback.apply(this, args);
      waiting = true;
      setTimeout(() => {
        waiting = false;
      }, limit);
    }
  };
}

export function moveAt(element, shiftX, shiftY) {
  Object.assign(element.style, {
    top: `${shiftY}px`,
    left: `${shiftX}px`,
  });
}
