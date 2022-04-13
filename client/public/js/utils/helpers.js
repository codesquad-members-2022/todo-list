export function qs(selector, scope = document) {
  return scope.querySelector(selector);
} // querySelector

export function qsAll(selector, scope = document) {
  return Array.from(scope.querySelectorAll(selector));
} // querySelectorAll and convert Array for easy to control

export function insertElement(target, option, element) {
  target.insertAdjacentHTML(option, element);
} // For Rendering

export function on(target, eventName, handler) {
  target.addEventListener(eventName, handler);
}

export function delegate(target, eventName, selector, handler) {
  const emitEvent = (event) => {
    const potentialElements = qsAll(selector, target);

    for (const potentialElement of potentialElements) {
      if (potentialElement === event.target) {
        return handler.call(event.target, event);
      }
    }
  };

  on(target, eventName, emitEvent);
}

export function formatRelativeDate(date = new Date()) {
  const TEN_SECOND = 10 * 1000;
  const A_MINUTE = 60 * 1000;
  const A_HOUR = 60 * A_MINUTE;
  const A_DAY = 24 * A_HOUR;
  const A_WEEK = 7 * A_DAY;

  const diff = new Date() - date;
  console.log(diff, A_WEEK);

  if (diff < TEN_SECOND) return `방금 전`;
  if (diff < A_MINUTE) return `${Math.floor(diff / 1000)}초 전`;
  if (diff < A_HOUR) return `${Math.floor(diff / 1000 / 60)}분 전`;
  if (diff < A_DAY) return `${Math.floor(diff / 1000 / 60 / 24)}시간 전`;
  if (diff < A_WEEK) return `${Math.floor(diff / 1000 / 60 / 24 / 7 / 7)}일 전`;
  return date.toLocaleString("ko-KR", {
    hour12: false,
    dateStyle: "medium",
  });
}

export function getParentElementByDataset(target, dataName) {
  const parentEl = target.parentElement;
  const dataValue = parentEl.dataset[dataName];

  if (dataValue) {
    return parentEl;
  }
  return getParentElementByDataset(parentEl, dataName);
}
