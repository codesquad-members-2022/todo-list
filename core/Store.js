export class Store extends WeakMap {
  #state;
  state;
  static KEY = Symbol();

  constructor(state) {
    super();
    this._state = state;
  }

  addView(view) {
    this.state = {};
    const state = view.initState();
    this.subscribe(state, view);

  }

  subscribe(state, view) {
    super.has(state) //super map임
      ? super.get(state).add(view)
      : super.set(state, new Set().add(view));

  }

  observe(state) {
    const isProxy = Symbol("isProxy");
    const handler = {
      get: (target, name, receiver) => {
        if (name === isProxy) return true;
        const prop = target[name];
        if (!prop) return undefined;
        if (!prop.isProxy && typeof prop == "object")
          return new Proxy(prop, handler);
        return target[name];
      },
      set: (target, name, value, receiver) => {
        if (target[name] == value) return true;
        Reflect.set(target, name, value);
        console.log("hi");
        if (super.has(receiver)) {
          super.get(receiver).forEach((view) => {
            view.render();
          });
        }
        return true;
      }
    };

    return new Proxy(state, handler);
  }
}
