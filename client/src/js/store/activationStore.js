const subscribers = {
  //   sidebar: [],
  //   add: [],
  //   alert: [],
};

const activation = {
  //   sidebar: false,
  //   add: false,
  //   alert: false,
};

export const subscribe = (key, notify) => {
  if (activation[key] === undefined) {
    activation[key] = false;
    let value = activation[key];
    Object.defineProperty(activation, key, {
      get: () => {
        return value;
      },
      set: (newValue) => {
        value = newValue;
        subscribers[key].forEach((notify) => {
          notify(activation[key]);
        });
      },
    });
  }

  subscribers[key] = subscribers[key] || [];
  subscribers[key].push(notify);
};

export const update = (key) => {
  activation[key] = !activation[key];
};
