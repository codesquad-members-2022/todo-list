const peact = (function () {
  const info = {
    currentStateKey: 0,
    state: {},
    isMounted: false,
    updatedStateKey: null,
    $root: null,
    app: null,
    rootComponent: null,
  };

  const createElement = ({ tag, id, className, attrs = null, child }) => {
    const $element = document.createElement(tag);
    if (id) {
      $element.id = id;
    }
    if (className) {
      if (typeof className === "string") {
        $element.classList.add(className);
      } else {
        $element.classList.add(...className);
      }
    }
    if (attrs) {
      Object.entries(attrs).forEach(([key, value]) => {
        if (key.includes("on")) {
          const [, eventType] = key.split("on");
          $element.addEventListener(eventType.toLowerCase(), value);
        } else {
          $element.setAttribute(key, value);
        }
      });
    }
    if (child.length === 1 && typeof child[0] === "string") {
      const [elementTextContent] = child;
      $element.innerHTML = elementTextContent;
    } else {
      child.forEach((childElement) => {
        $element.appendChild(childElement);
      });
    }
    return $element;
  };

  const setRoot = ($root) => {
    info.$root = $root;
  };

  const render = (component) => {
    if (!info.app) {
      info.app = component;
    }
    info.currentStateKey = 0;
    info.$root.innerHTML = "";
    info.$root.appendChild(info.app());
  };

  const useEffect = (callback, watchStates) => {
    const isUpdate = watchStates.some((wValue) => {
      return wValue === info.state[info.updatedStateKey];
    });
    const isMounted = !(info.updatedStateKey === null);
    if (isUpdate || !isMounted) {
      setTimeout(() => {
        callback();
      }, 0);
    }
  };

  const useState = (defaultValue) => {
    const { currentStateKey } = info;
    const isNewUseState = Object.values(info.state).length === currentStateKey;
    if (isNewUseState) {
      info.state[currentStateKey] = defaultValue;
    }
    const value = info.state[currentStateKey];
    const setValue = (newValue) => {
      setTimeout(() => {
        info.state[currentStateKey] = newValue;
        info.updatedStateKey = currentStateKey;
        render();
      }, 0);
    };
    info.currentStateKey += 1;
    return [value, setValue];
  };

  return { setRoot, useState, useEffect, render, createElement };
})();

export default peact;
