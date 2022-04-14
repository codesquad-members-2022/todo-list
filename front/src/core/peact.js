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

  const addClassName = ($el, className) => {
    if (typeof className === "string") {
      $el.classList.add(className);
    } else {
      $el.classList.add(...className);
    }
  };

  const addEvent = ($el, onEventType, callback) => {
    const [, eventType] = onEventType.split("on");
    $el.addEventListener(eventType.toLowerCase(), callback);
  };

  const useRef = () => {
    return {
      current: null,
    };
  };

  const createElement = ({ tag, id, ref, className, attrs = null, child }) => {
    const $element = document.createElement(tag);
    if (id) {
      $element.id = id;
    }
    if (className) {
      addClassName($element, className);
    }
    if (attrs) {
      Object.entries(attrs).forEach(([key, value]) => {
        if (key.includes("on")) {
          addEvent($element, key, value);
        } else {
          $element.setAttribute(key, value);
        }
      });
    }
    const isStringChildElement =
      child.length === 1 && typeof child[0] === "string";

    if (isStringChildElement) {
      const [elementTextContent] = child;
      $element.innerHTML = elementTextContent;
    } else {
      child.forEach((childElement) => {
        $element.appendChild(childElement);
      });
    }
    if (ref) {
      ref.current = $element;
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

  const executeAfterStackEmpty = (callback) => {
    setTimeout(callback, 0);
  };

  const useEffect = (callback, dependencyList) => {
    const isUpdate = dependencyList.some((wValue) => {
      return wValue === info.state[info.updatedStateKey];
    });
    const isMounted = !(info.updatedStateKey === null);
    if (isUpdate || !isMounted) {
      executeAfterStackEmpty(callback);
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
      executeAfterStackEmpty(() => {
        info.state[currentStateKey] = newValue;
        info.updatedStateKey = currentStateKey;
        render();
      });
    };
    info.currentStateKey += 1;
    return [value, setValue];
  };

  return { setRoot, useState, useEffect, render, createElement, useRef };
})();

export default peact;
